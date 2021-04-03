package com.tenorinho.consultacnpj.ui.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment
import com.tenorinho.consultacnpj.R
import com.tenorinho.consultacnpj.data.model.dto.db.DBEmpresa

class ShowEmpresaDialogFragment : AppCompatDialogFragment {
    private var empresa: DBEmpresa? = null
    constructor(empresa: DBEmpresa):super(){
        this.empresa = empresa
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val b = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.show_empresa_dialog, null)
        dialogView.findViewById<TextView>(R.id.show_empresa_bairro).text = empresa?.bairro ?: "---"
        dialogView.findViewById<TextView>(R.id.show_empresa_cep).text = empresa?.cep ?: "---"
        dialogView.findViewById<TextView>(R.id.show_empresa_cnpj).text = empresa?.cnpj ?: "---"
        dialogView.findViewById<TextView>(R.id.show_empresa_data_abertura).text = empresa?.dataAbertura ?: "---"
        dialogView.findViewById<TextView>(R.id.show_empresa_endereco).text = empresa?.logradouro ?: "---"
        dialogView.findViewById<TextView>(R.id.show_empresa_razao_social).text = empresa?.razaoSocial ?: "---"
        dialogView.findViewById<TextView>(R.id.show_empresa_natu_jur).text = empresa?.naturezaJuridica ?: "---"
        dialogView.findViewById<TextView>(R.id.show_empresa_nome_fantasia).text = empresa?.nomeFantasia ?: "---"
        dialogView.findViewById<TextView>(R.id.show_empresa_uf).text = empresa?.unidadeDaFederacao ?: "---"
        dialogView.findViewById<TextView>(R.id.show_empresa_municipio).text = empresa?.municipio ?: "---"
        dialogView.findViewById<TextView>(R.id.show_empresa_tipo).text = empresa?.tipo ?: "---"
        dialogView.findViewById<TextView>(R.id.show_empresa_situacao).text = empresa?.situacao ?: "---"
        b.setView(dialogView).setNegativeButton("OK", null)
        return b.create()
    }
}