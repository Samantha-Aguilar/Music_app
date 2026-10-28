package com.example.music_app.network

import com.example.music_app.model.ITunesResponse
import retrofit2.http.GET
import retrofit2.http.Query

//archivo que permite generar la peticion al api
interface ITunesApi {

    @GET("search")

    //metodo para buscar el album por genero
    fun obtenerAlbumGenero(
        @Query("term") genero: String,
        @Query("country")  pais: String,
        @Query("media") media : String,
        @Query("entity") entidad : String,
        @Query("limit") limite : Int,
        ) : retrofit2.Call<ITunesResponse>



}