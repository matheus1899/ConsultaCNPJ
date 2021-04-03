package com.tenorinho.consultacnpj.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tenorinho.consultacnpj.R
import com.tenorinho.consultacnpj.data.model.dto.db.DBEmpresa

class CNPJAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private var listaEmpresa:ArrayList<DBEmpresa>
    constructor():super(){
        listaEmpresa = ArrayList()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_cnpj, parent, false)
        return CNPJViewHolder(view)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        val viewHolder = holder as CNPJViewHolder
        viewHolder.bind(listaEmpresa[position])
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
        constructor(v:View):super(v){
            txtCNPJ = v.findViewById(R.id.item_txt_cnpj)
            txtRazaoSocial = v.findViewById(R.id.item_txt_razao_social)
            txtNomeFantasia = v.findViewById(R.id.item_txt_nome_fantasia)
        }
        fun bind(e:DBEmpresa){
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