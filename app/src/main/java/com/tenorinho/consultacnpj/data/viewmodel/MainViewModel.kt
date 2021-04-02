package com.tenorinho.consultacnpj.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tenorinho.consultacnpj.data.model.domain.Empresa
import com.tenorinho.consultacnpj.data.repository.EmpresaRepository
import kotlinx.coroutines.launch

class MainViewModel(private val empresaRepo: EmpresaRepository) : ViewModel(){
    val listaEmpresas = MutableLiveData<ArrayList<Empresa>>()
    val progressBarIsVisible = MutableLiveData<Boolean>()
    val cnpjText = MutableLiveData<String>()
    val error = MutableLiveData<Throwable>()

    init {
        listaEmpresas.value = ArrayList(0)
        progressBarIsVisible.value = false
        cnpjText.value = ""
    }
    fun bindListaEmpresa(lista: ArrayList<Empresa>?){
        if(lista != null){
            listaEmpresas.value!!.addAll(lista)
        }
    }
    fun bindError(t:Throwable){
        error.value = t
    }
    fun searchByCNPJ(){
        viewModelScope.launch {}
    }
    fun getAllEmpresas(){
        viewModelScope.launch {
            empresaRepo.getAllEmpresas(::bindListaEmpresa, ::bindError)
        }
    }
    class MainViewModelFactory(private val empresaRepo: EmpresaRepository):ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(empresaRepo) as T
            }
            throw IllegalArgumentException("Class ViewModel desconhecida")
        }
    }
}