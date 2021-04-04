package com.tenorinho.consultacnpj.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tenorinho.consultacnpj.data.model.domain.Empresa
import com.tenorinho.consultacnpj.data.model.dto.db.DBEmpresa
import com.tenorinho.consultacnpj.data.repository.EmpresaRepository
import kotlinx.coroutines.launch

class ShowEmpresaViewModel(private val repositorio:EmpresaRepository) : ViewModel() {
    val empresa = MutableLiveData<Empresa>()
    val isFromWeb = MutableLiveData<Boolean>()
    val success = MutableLiveData<String>()

    init {
        repositorio.scope = viewModelScope
        isFromWeb.value = false
        success.value = ""
    }
    private fun onSuccessAddEmpresa(message:String){
        if(message.isNotEmpty()){
            success.value = message
            isFromWeb.value = false
        }
    }
    fun saveEmpresa(){
        val e = empresa.value
        if(e != null){
            val db = DBEmpresa(0, e.cnpj, e.tipo, e.dataAbertura, e.razaoSocial,
                e.nomeFantasia, e.naturezaJuridica, e.situacao, e.logradouro, e.complemento,
                e.cep, e.bairro, e.municipio, e.unidadeDaFederacao)
            viewModelScope.launch {
                repositorio.saveEmpresa(db, ::onSuccessAddEmpresa)
            }
        }
    }
    class ShowEmpresaViewModelFactory(private val repository: EmpresaRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ShowEmpresaViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ShowEmpresaViewModel(repository) as T
            }
            throw IllegalArgumentException("Class ViewModel desconhecida")
        }
    }
}