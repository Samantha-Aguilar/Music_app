package com.example.music_app.model

import android.os.Debug

//archivo para verificar si obtenemos una respuesta del api
data class ITunesResponse(
    val resultCount: Int,
    val results: List<Album>
)
