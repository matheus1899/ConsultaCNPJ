package com.tenorinho.consultacnpj.data.repository

import com.tenorinho.consultacnpj.data.localdb.dao.IEmpresaDAO
import com.tenorinho.consultacnpj.data.model.domain.Empresa
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class EmpresaRepository(private val dao:IEmpresaDAO, private val scope:CoroutineScope){
    suspend fun getEmpresaByCNPJ(cnpj: String, success: (Empresa?) -> Unit, failure: (Throwable) -> Unit) {
        failure(Throwable("getEmpresaByCNPJ não implementado"))
    }
    suspend fun getAllEmpresas(success:(ArrayList<Empresa>?)->Unit, failure:(Throwable)->Unit){
        try {
            scope.launch{
                    val lista = dao.getAllEmpresas()
                    val lista2 = ArrayList<Empresa>(lista.size)
                    for(i in lista){
                        lista2.add(i.toEmpresa())
                    }
                    success(lista2)
                }
            }
        catch(e:Exception){
            failure(Throwable(e.message))
        }
    }
}