package com.tenorinho.consultacnpj.data.net

import com.tenorinho.consultacnpj.data.model.dto.network.NetworkEmpresa
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IReceitaService {
    @GET("{cnpj}")
    fun getEmpresaByCNPJ(@Path("cnpj")cnpj:String): Call<NetworkEmpresa>
}