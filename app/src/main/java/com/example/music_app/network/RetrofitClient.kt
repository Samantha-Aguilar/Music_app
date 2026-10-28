package com.example.music_app.network

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//archivo para construir todo el enlace del api
object RetrofitClient {

    //colocar la base URL
    private const val BASE_URL = "https://itunes.apple.com/"

    //contruir la url completa
    val api : ITunesApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ITunesApi::class.java)
    }
}