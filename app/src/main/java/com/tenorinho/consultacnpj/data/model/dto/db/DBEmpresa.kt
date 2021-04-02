package com.tenorinho.consultacnpj.data.model.dto.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tenorinho.consultacnpj.data.model.domain.AtividadeEmpresa
import com.tenorinho.consultacnpj.data.model.domain.Empresa

@Entity(tableName = "table_empresas")
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
    val logradouro:String,
    val complemento:String,
    val cep:String,
    val bairro:String,
    val municipio:String,
    val unidadeDaFederacao:String,
){
    fun toEmpresa():Empresa{
        val list = atividadePrincipal.split("|")
        val list2 = atividadesSecundarias.split("|")
        val aP = ArrayList<AtividadeEmpresa>((list.size/2).toInt())
        val aS = ArrayList<AtividadeEmpresa>((list2.size/2).toInt())

        for(i in 0..list.size/2){
            aP.add(AtividadeEmpresa(list[i], list[i+1]))
        }
        for(i in 0..list2.size/2){
            aS.add(AtividadeEmpresa(list[i], list[i+1]))
        }
        return Empresa(id, cnpj, tipo, dataAbertura, razaoSocial, nomeFantasia, aP, aS,
            naturezaJuridica, situacao, logradouro, complemento, cep, bairro, municipio, unidadeDaFederacao)
    }
}