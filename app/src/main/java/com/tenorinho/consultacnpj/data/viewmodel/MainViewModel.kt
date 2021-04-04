package com.tenorinho.consultacnpj.data.viewmodel

import androidx.lifecycle.*
import com.tenorinho.consultacnpj.data.model.dto.db.DBEmpresa
import com.tenorinho.consultacnpj.data.repository.EmpresaRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: EmpresaRepository) : ViewModel(){
    lateinit var listaEmpresas:LiveData<List<DBEmpresa>>
    val empresa = MutableLiveData<DBEmpresa>()
    val progressBarIsVisible = MutableLiveData<Boolean>()
    val cnpjIsValid = MutableLiveData<Boolean>()
    val cnpjText = MutableLiveData<String>()
    val error = MutableLiveData<Throwable>()
    val shakeShake = MutableLiveData<Boolean>()
    var isFromWeb:Boolean

    init{
        progressBarIsVisible.value = false
        cnpjIsValid.value = false
        shakeShake.value = true
        isFromWeb = false
        cnpjText.value = ""
        repository.scope = viewModelScope
        updateList()
    }
    fun searchCNPJ(){
        progressBarIsVisible.value = true
        if(cnpjIsValid.value!!){
            viewModelScope.launch {
                var cnpjComPonto:String = ""
                cnpjComPonto += cnpjText.value!!.subSequence(0, 2)
                cnpjComPonto += "."
                cnpjComPonto += cnpjText.value!!.subSequence(2, 5)
                cnpjComPonto += "."
                cnpjComPonto += cnpjText.value!!.subSequence(5, 8)
                cnpjComPonto += "/"
                cnpjComPonto += cnpjText.value!!.subSequence(8, 12)
                cnpjComPonto += "-"
                cnpjComPonto += cnpjText.value!!.subSequence(12, 14)

                repository.getEmpresaByCNPJ(cnpjText.value!!, cnpjComPonto, ::bindEmpresa, ::bindError)
            }
        }
        else{
            shakeShake.value = !shakeShake.value!!
        }
        progressBarIsVisible.value = false
    }
    fun bindEmpresa(e:DBEmpresa?, fromWeb:Boolean){
        if(e != null){
            isFromWeb = fromWeb
            empresa.value = e!!
        }
    }
    fun updateList(){
        viewModelScope.launch {
            repository.getAllEmpresas(::bindListaEmpresas, ::bindError)
        }
    }
    fun updateEmpresa(position:Int){
        val e = listaEmpresas.value!![position]
        if(e != null){
            empresa.value = e
        }
    }
    fun bindListaEmpresas(l:LiveData<List<DBEmpresa>>?){
        if(l != null){
            listaEmpresas =  l
        }
    }
    fun bindError(t:Throwable){
        error.value = t
    }
    class MainViewModelFactory(private val repository: EmpresaRepository):ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(repository) as T
            }
            throw IllegalArgumentException("Class ViewModel desconhecida")
        }
    }
}