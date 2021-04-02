package com.tenorinho.consultacnpj.data.model.domain

data class Empresa(
    val id:Int = 0,
    val cnpj:String,
    val tipo:String,
    val dataAbertura:String,
    val razaoSocial:String,
    val nomeFantasia:String,
    val atividadePrincipal:ArrayList<AtividadeEmpresa>,
    val atividadesSecundarias: ArrayList<AtividadeEmpresa>,
    val naturezaJuridica:String,
    val situacao:String,
    //Endereço
    val logradouro:String,
    val complemento:String,
    val cep:String,
    val bairro:String,
    val municipio:String,
    val unidadeDaFederacao:String,
)
data class AtividadeEmpresa(
    val texto:String,
    val codigo:String
)
