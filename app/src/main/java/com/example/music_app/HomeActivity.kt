package com.example.music_app

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.music_app.model.ITunesResponse
import com.example.music_app.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.get

class HomeActivity : AppCompatActivity() {
    private lateinit var tvResulado: TextView
    private lateinit var imgAlbum: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        tvResulado = findViewById(R.id.tvResultado)
        imgAlbum = findViewById(R.id.imgAlbum)

        obtenerAlbum()
    }

    private fun obtenerAlbum(){

        RetrofitClient.api.obtenerAlbumGenero(
            genero = "indie",
            pais = "MX",
            media ="music",
            entidad = "album",
            limite  = 1,

            ).enqueue(object : Callback<ITunesResponse?> {

            //METODO PARA VERIFICAR QUE LA RESPUESTA SEA CORRECTA
            override fun onResponse(call: Call<ITunesResponse?>, response: Response<ITunesResponse?>) {

                //Variable que obtenga los resultados del appi
                val albumes = response.body()?.results
                if (!albumes.isNullOrEmpty()){

                    //variable que guarde la informacion de cada respuesta obtenidad del api
                    val  album = albumes[0]

                    //mostrar informacion del api
                    tvResulado.text = "Nombre del a;bum: ${album.collectionName} \n\n" +
                            "Artista: ${album.artistName}"

                    //mostrar imagen en al api
                    Glide.with(this@HomeActivity)
                        .load(album.obtenerImagenGrande())
                        .into(imgAlbum)
                }else{
                    Toast.makeText(this@HomeActivity, "No hay resultados", Toast.LENGTH_SHORT).show()

                }
            }

            //METODO PARA IDENTIFICAR SI NO HAY RESOUESTA

            override fun onFailure(call: Call<ITunesResponse?>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "ERROR EN EL API", Toast.LENGTH_SHORT).show()



            }
        })
    }

}