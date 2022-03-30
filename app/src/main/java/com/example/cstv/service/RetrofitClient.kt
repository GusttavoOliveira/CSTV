package com.example.cstv.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor(){

    companion object{

        private lateinit var retrofit: Retrofit
        private const val pandaScoreUrl = "https://api.pandascore.co/"

        private fun getRetrofitInstance(): Retrofit{

            val httpClient = OkHttpClient.Builder()
            if (!::retrofit.isInitialized){
                retrofit = Retrofit.Builder().baseUrl(pandaScoreUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }

    }
}