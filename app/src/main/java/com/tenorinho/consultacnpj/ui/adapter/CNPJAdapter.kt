package com.tenorinho.consultacnpj.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tenorinho.consultacnpj.R
import com.tenorinho.consultacnpj.data.model.dto.db.DBEmpresa
import com.tenorinho.consultacnpj.data.viewmodel.MainViewModel
import com.tenorinho.consultacnpj.ui.activity.MainActivity

class CNPJAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private var listaEmpresa:ArrayList<DBEmpresa>
    private var mainActivity:MainActivity

    constructor(mainActivity: MainActivity):super(){
        listaEmpresa = ArrayList()
        this.mainActivity = mainActivity
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_cnpj, parent, false)
        return CNPJViewHolder(view, mainActivity)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        val viewHolder = holder as CNPJViewHolder
        viewHolder.bind(listaEmpresa[position], position)
    }
    override fun getItemCount(): Int {
        return listaEmpresa.size
    }
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
    fun updateList(l:List<DBEmpresa>?){
        if(l != null){
            listaEmpresa = ArrayList(l)
            notifyDataSetChanged()
        }
    }
    private class CNPJViewHolder: RecyclerView.ViewHolder{
        val txtCNPJ:TextView
        val txtRazaoSocial:TextView
        val txtNomeFantasia:TextView
        val rootView:View
        val mainActivity:MainActivity
        constructor(v:View, mainActivity: MainActivity):super(v){
            rootView = v
            this.mainActivity = mainActivity
            txtCNPJ = v.findViewById(R.id.item_txt_cnpj)
            txtRazaoSocial = v.findViewById(R.id.item_txt_razao_social)
            txtNomeFantasia = v.findViewById(R.id.item_txt_nome_fantasia)
        }
        fun bind(e:DBEmpresa, position: Int){
            rootView.setOnClickListener { mainActivity.updateEmpresa(position) }
            txtCNPJ.text = e.cnpj
            txtRazaoSocial.text = e.razaoSocial
            if(!e.nomeFantasia.isNullOrBlank()){
                txtNomeFantasia.text = e.nomeFantasia
            }
            else{
                txtNomeFantasia.visibility = View.GONE
            }
        }
    }
}