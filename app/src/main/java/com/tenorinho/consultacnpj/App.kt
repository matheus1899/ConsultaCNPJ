package com.tenorinho.consultacnpj

import android.app.Application
import com.tenorinho.consultacnpj.data.localdb.LocalDB
import com.tenorinho.consultacnpj.data.localdb.dao.IEmpresaDAO

class App:Application(){
    val db : LocalDB by lazy { LocalDB.getInstancia(this) }
    val empresaDAO : IEmpresaDAO by lazy { db.getIEmpresaDAO() }
}