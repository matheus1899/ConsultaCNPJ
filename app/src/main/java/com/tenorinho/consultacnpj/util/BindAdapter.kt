package com.tenorinho.consultacnpj.util

import android.view.View
import androidx.databinding.BindingAdapter

class BindAdapter{
    companion object{
        @BindingAdapter("onClick")
        @JvmStatic fun onClick(view: View, onClick:() -> Unit){
            view.setOnClickListener{
                onClick()
            }
        }
    }
}