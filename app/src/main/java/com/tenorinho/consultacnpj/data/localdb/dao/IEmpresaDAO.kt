package com.tenorinho.consultacnpj.data.localdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tenorinho.consultacnpj.data.model.dto.db.DBEmpresa

@Dao interface IEmpresaDAO {
    @Query("SELECT * FROM table_empresas")
    suspend fun getAllEmpresas():List<DBEmpresa>
    @Query("SELECT * FROM table_empresas WHERE cnpj LIKE :cnpj")
    suspend fun getEmpresaByCNPJ(cnpj:String):DBEmpresa
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEmpresa(empresa:DBEmpresa)
}