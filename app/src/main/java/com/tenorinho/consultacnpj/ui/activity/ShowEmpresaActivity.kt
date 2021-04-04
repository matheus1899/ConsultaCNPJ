package com.tenorinho.consultacnpj.ui.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tenorinho.consultacnpj.App
import com.tenorinho.consultacnpj.R
import com.tenorinho.consultacnpj.data.model.domain.Empresa
import com.tenorinho.consultacnpj.data.repository.EmpresaRepository
import com.tenorinho.consultacnpj.data.viewmodel.ShowEmpresaViewModel
import com.tenorinho.consultacnpj.databinding.ActivityShowEmpresaBinding

class ShowEmpresaActivity : AppCompatActivity() {
    private lateinit var binding:ActivityShowEmpresaBinding
    private lateinit var viewModel:ShowEmpresaViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_empresa)
        init()
    }
    private fun init(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getViewModel()
        viewModel.isFromWeb.observe(this, Observer{ setButtonVisibility()})
        val b = intent.getBundleExtra("bundle")
        if(b != null){
            if(b.containsKey("isFromWeb")){
                val bool = b.getBoolean("isFromWeb")
                viewModel.isFromWeb.value = bool
            }
            if(b.containsKey("empresa")){
                val e = b.getSerializable("empresa") as Empresa
                viewModel.empresa.value = e
            }
        }
        binding.viewModel = viewModel
    }
    private fun getViewModel(){
        val app = application as App
        val factory = ShowEmpresaViewModel.ShowEmpresaViewModelFactory(EmpresaRepository(app.empresaDAO))
        viewModel = ViewModelProvider(this, factory).get(ShowEmpresaViewModel::class.java)
    }
    private fun setButtonVisibility(){
        binding.showEmpresaBtnSalvar.visibility = if(viewModel.isFromWeb.value!!) View.VISIBLE else View.GONE
    }
}