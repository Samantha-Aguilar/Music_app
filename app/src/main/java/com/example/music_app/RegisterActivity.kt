package com.example.music_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etCorreo : EditText
    private lateinit var etPassword : EditText

    private lateinit var btnRegistrar: Button

    //crear objeto de la clase de datos
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        etNombre =findViewById(R.id.etNombre)
        etCorreo =findViewById(R.id.etCorreo)
        etPassword =findViewById(R.id.etPassword)
        btnRegistrar=findViewById(R.id.btnRegistrar)
        //iniciar el objeto de la base de datos
        dbHelper= DatabaseHelper(this)

        //evento para registrar a un usuario
        btnRegistrar.setOnClickListener {
            //llamar al metodo de registrar usuario
            registrarUsuario()
        }

    }

    //metodo para registrar usuario
    private fun registrarUsuario(){
        //obtener la informacion que escribe el usuario
        val nombre = etNombre.text.toString().trim()
        val correo = etCorreo.text.toString().trim()
        val password = etPassword.text.toString().trim()
        //Validar que los campos tengan informacion
        if(nombre.isEmpty() || correo.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Completa todo los campos", Toast.LENGTH_SHORT).show()
            return
        }
        //llamar a nuestra base de datos y guardar el resultado de la insercion
        val resultado = dbHelper.registrarUsuario(nombre, correo, password)
        if(resultado){
            //si el resultado es verdadero
            Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show()
            //Regresar al usuario a la pantalla anterior
            finish()
        }else{
            Toast.makeText(this,"Registro fallido", Toast.LENGTH_SHORT).show()
        }
    }
}