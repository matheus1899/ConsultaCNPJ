package com.tenorinho.consultacnpj.data.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    companion object{
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://www.receitaws.com.br/v1/cnpj/")
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}