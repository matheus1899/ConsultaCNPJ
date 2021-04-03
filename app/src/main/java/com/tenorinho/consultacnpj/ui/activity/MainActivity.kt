package com.tenorinho.consultacnpj.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tenorinho.consultacnpj.App
import com.tenorinho.consultacnpj.R
import com.tenorinho.consultacnpj.data.repository.EmpresaRepository
import com.tenorinho.consultacnpj.data.viewmodel.MainViewModel
import com.tenorinho.consultacnpj.databinding.ActivityMainBinding
import com.tenorinho.consultacnpj.ui.adapter.CNPJAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter:CNPJAdapter
    private lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }
    private fun init(){
        adapter = CNPJAdapter()
        setViewModel()
        binding.recyclerView.adapter = adapter
        binding.viewModel = viewModel
        viewModel.error.observe(this, Observer{ Toast.makeText(this, it.message,Toast.LENGTH_SHORT).show() })
        viewModel.empresa.observe(this, Observer{ showDBEmpresa() })
        viewModel.cnpjText.observe(this, Observer{ validarCNPJ() })
        viewModel.shakeShake.observe(this, Observer{ shakeShake() })
        viewModel.listaEmpresas.observe(this, Observer{ adapter.updateList(it) })
        viewModel.progressBarIsVisible.observe(this, Observer { showOrHideProgressBar(it) })
        adapter.updateList(viewModel.listaEmpresas.value)
    }
    private fun setViewModel(){
        val app = application as App
        val factory = MainViewModel.MainViewModelFactory(EmpresaRepository(app.empresaDAO))
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }
    private fun validarCNPJ(){
        if(viewModel.cnpjText.value != null &&
            viewModel.cnpjIsValid.value != null) {

            if (viewModel.cnpjText.value!!.length != 14) {
                if (viewModel.cnpjIsValid.value!!) {
                    binding.mainEdtCnpj.setTextColor(resources.getColor(R.color.Invalid))
                    viewModel.cnpjIsValid.value = false
                }
            }
            else {
                val charArray = viewModel.cnpjText.value!!.toCharArray()
                val pesoArray = arrayOf(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2)
                val intArray = IntArray(14)
                for (i in 0..13) {
                    val inteiro = charArray[i].toString().toIntOrNull()
                    if (inteiro != null) {
                        intArray[i] = inteiro
                    } else {
                        if (viewModel.cnpjIsValid.value!!) {
                            binding.mainEdtCnpj.setTextColor(resources.getColor(R.color.Invalid))
                            viewModel.cnpjIsValid.value = false
                        }
                        return
                    }
                }
                var soma = 0
                for (i in 0..11) {
                    soma += pesoArray[i] * intArray[i]
                }
                var resto = soma % 11
                var primeiroDigito = 0
                if (resto >= 2) {
                    primeiroDigito = 11 - resto
                }
                if (intArray[12] != primeiroDigito) {
                    if (viewModel.cnpjIsValid.value!!) {
                        binding.mainEdtCnpj.setTextColor(resources.getColor(R.color.Invalid))
                        viewModel.cnpjIsValid.value = false
                    }
                    return
                }
                val pesoArray2 = arrayOf(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2)
                soma = 0

                for (i in 0..12) {
                    soma += pesoArray2[i] * intArray[i]
                }
                resto = soma % 11
                var segundoDigito: Int
                if (resto < 2) {
                    segundoDigito = 0
                } else {
                    segundoDigito = 11 - resto
                }
                if (intArray[13] != segundoDigito) {
                    if (viewModel.cnpjIsValid.value!!) {
                        binding.mainEdtCnpj.setTextColor(resources.getColor(R.color.Invalid))
                        viewModel.cnpjIsValid.value = false
                    }
                    return
                } else {
                    binding.mainEdtCnpj.setTextColor(resources.getColor(R.color.Valid))
                    viewModel.cnpjIsValid.value = true
                }
            }
        }
    }
    private fun showDBEmpresa(){
        //abrir Dialog com Imformações da Empresa
    }
    private fun showOrHideProgressBar(b:Boolean){
        binding.progressBar.visibility = if(b) View.VISIBLE else View.GONE
    }
    private fun shakeShake(){
        if(viewModel.cnpjText.value!!.isNotEmpty()){
            Toast.makeText(this, "CNPJ inválido", Toast.LENGTH_SHORT).show()
        }
    }
}