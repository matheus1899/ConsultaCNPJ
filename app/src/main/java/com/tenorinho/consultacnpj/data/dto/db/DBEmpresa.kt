package com.tenorinho.consultacnpj.data.dto.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "empresa")
data class DBEmpresa(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val cnpj:String,
    val tipo:String,
    val dataAbertura:String,
    val razaoSocial:String,
    val nomeFantasia:String,
    val atividadePrincipal:String,
    val atividadesSecundarias:String,
    val naturezaJuridica:String,
    val situacao:String,
    val complemento:String,
    val cep:String,
    val bairro:String,
    val municipio:String,
    val unidadeDaFederacao:String,
)