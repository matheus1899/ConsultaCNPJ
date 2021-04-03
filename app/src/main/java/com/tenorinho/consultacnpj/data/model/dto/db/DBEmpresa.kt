package com.tenorinho.consultacnpj.data.model.dto.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tenorinho.consultacnpj.data.model.domain.AtividadeEmpresa
import com.tenorinho.consultacnpj.data.model.domain.Empresa

@Entity(tableName = "table_empresas")
data class DBEmpresa(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val cnpj:String,
    val tipo:String,
    val dataAbertura:String,
    val razaoSocial:String,
    val nomeFantasia:String,
    //val atividadePrincipal:String,
    //val atividadesSecundarias:String,
    val naturezaJuridica:String,
    val situacao:String,
    val logradouro:String,
    val complemento:String,
    val cep:String,
    val bairro:String,
    val municipio:String,
    val unidadeDaFederacao:String,
){
    fun toEmpresa():Empresa{
        return Empresa(id, cnpj, tipo, dataAbertura, razaoSocial, nomeFantasia,
            naturezaJuridica, situacao, logradouro, complemento, cep, bairro, municipio, unidadeDaFederacao)
    }
}