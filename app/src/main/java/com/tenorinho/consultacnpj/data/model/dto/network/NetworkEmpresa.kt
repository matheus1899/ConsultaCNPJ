package com.tenorinho.consultacnpj.data.model.dto.network

import com.google.gson.annotations.SerializedName

data class NetworkEmpresa(
    @SerializedName("cnpj")
    val cnpj:String,
    @SerializedName("tipo")
    val tipo:String,
    @SerializedName("abertura")
    val dataAbertura:String,
    @SerializedName("nome")
    val razaoSocial:String,
    @SerializedName("fantasia")
    val nomeFantasia:String,
    @SerializedName("atividade_principal")
    val atividadePrincipal:ArrayList<NetworkAtividadeEmpresa>,
    @SerializedName("atividades_secundarias")
    val atividadesSecundarias: ArrayList<NetworkAtividadeEmpresa>,
    @SerializedName("natureza_juridica")
    val naturezaJuridica:String,
    @SerializedName("situacao")
    val situacao:String,
    //Endereço
    @SerializedName("logradouro")
    val logradouro:String,
    @SerializedName("complemento")
    val complemento:String,
    @SerializedName("cep")
    val cep:String,
    @SerializedName("bairro")
    val bairro:String,
    @SerializedName("municipio")
    val municipio:String,
    @SerializedName("uf")
    val unidadeDaFederacao:String,
)
data class NetworkAtividadeEmpresa(
    @SerializedName("text")
    val texto:String,
    @SerializedName("code")
    val codigo:String
)