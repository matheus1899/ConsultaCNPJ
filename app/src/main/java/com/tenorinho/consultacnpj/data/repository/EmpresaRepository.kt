package com.tenorinho.consultacnpj.data.repository

import androidx.lifecycle.LiveData
import com.tenorinho.consultacnpj.data.localdb.dao.IEmpresaDAO
import com.tenorinho.consultacnpj.data.model.domain.Empresa
import com.tenorinho.consultacnpj.data.model.dto.db.DBEmpresa
import com.tenorinho.consultacnpj.data.model.dto.network.NetworkEmpresa
import com.tenorinho.consultacnpj.data.net.IReceitaService
import com.tenorinho.consultacnpj.data.net.RetrofitConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class EmpresaRepository(private val dao:IEmpresaDAO){
    private val service = RetrofitConfig.getRetrofit().create(IReceitaService::class.java)
    var scope:CoroutineScope? = null

    suspend fun getEmpresaByCNPJ(cnpjSemPontuacao: String, cnpjComPontuacao:String, success: (DBEmpresa?) -> Unit, failure: (Throwable) -> Unit) {
        try {
            if(cnpjExists(cnpjComPontuacao)){
                val e = dao.getEmpresaByCNPJ(cnpjComPontuacao)
                success(e)
            }
            else{
                val c = service.getEmpresaByCNPJ(cnpjSemPontuacao)
                c.enqueue(object: Callback<NetworkEmpresa>{
                    override fun onResponse(call: Call<NetworkEmpresa>, response: Response<NetworkEmpresa>) {
                        if(response.code() == 200){
                            val body = response.body()
                            if(body != null && body.tipo != null && body.razaoSocial != null){
                                val e = DBEmpresa(0, body.cnpj, body.tipo, body.dataAbertura, body.razaoSocial, body.nomeFantasia,
                                    body.naturezaJuridica, body.situacao, body.logradouro, body.complemento, body.cep, body.bairro,
                                    body.municipio, body.unidadeDaFederacao)
                                scope?.launch { dao.addEmpresa(e) }
                                success(e)
                            }
                            else{
                                failure(Throwable("CNPJ não encontrado"))
                            }
                        }
                        else{
                            failure(Throwable(response.message()))
                        }
                    }
                    override fun onFailure(call: Call<NetworkEmpresa>, t: Throwable) {
                        failure(t)
                    }
                })
            }
        }
        catch(ex:Exception){
            failure(Throwable(ex.message))
        }
    }
    fun getAllEmpresas(success:(LiveData<List<DBEmpresa>>) -> Unit, failure:(Throwable) -> Unit){
        try {
            val l = dao.getAllEmpresas()
            success(l)
        }
        catch(ex:Exception){
            failure(Throwable(ex.message))
        }
    }
    private suspend fun cnpjExists(cnpj: String):Boolean{
        val i = dao.cnpjExists(cnpj)
        return i > 0
    }
}