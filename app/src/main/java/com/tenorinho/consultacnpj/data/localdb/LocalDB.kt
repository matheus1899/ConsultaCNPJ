package com.tenorinho.consultacnpj.data.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tenorinho.consultacnpj.data.localdb.dao.IEmpresaDAO
import com.tenorinho.consultacnpj.data.model.dto.db.DBEmpresa

@Database(entities = arrayOf(DBEmpresa::class), version = 1, exportSchema = false)
abstract class LocalDB :RoomDatabase(){
    abstract fun getIEmpresaDAO():IEmpresaDAO

    companion object{
        @Volatile private var INSTANCIA:LocalDB? = null
        fun getInstancia(context: Context):LocalDB{
            return INSTANCIA ?: synchronized(this){
                val instance:LocalDB = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDB::class.java,
                    "local_database"
                ).build()
                INSTANCIA = instance
                instance
            }
        }
    }
}