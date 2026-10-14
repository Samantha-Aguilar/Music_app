package com.example.music_app

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class DatabaseHelper (context: Context): SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION,
){
    companion object{
        private const val DATABASE_NAME = "Musica.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USUARIOS="usuarios"

        private const val COL_ID= "id"
        private const val COL_NOMBRE= "nombre"
        private const val COL_CORREO= "correo"
        private const val COL_PASSWORD= "password"

    }

    //metodo para crear la base de datos
    override fun onCreate(db: SQLiteDatabase?) {
        val crearTablaUsuarios ="""
            CREATE TABLE $TABLE_USUARIOS(
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_NOMBRE TEXT NOT NULL,
                $COL_CORREO TEXT NOT NULL UNIQUE,
                $COL_PASSWORD TEXT NOT NULL
            )
        """.trimIndent()

        //crear tabla de sql
        db?.execSQL(crearTablaUsuarios)
    }
    //metodo para actualizar la base de datos
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS")
        onCreate(db)
    }

    //metodo para insertar registro

    fun registrarUsuario(nombre: String, correo: String,password: String): Boolean{
        //crear variable para que la base de datos se pueda modificar
        val db= writableDatabase
        //crear variable para recibir los datos que crea el usuario
        val datos = ContentValues().apply {
            put(COL_NOMBRE,nombre)
            put(COL_CORREO, correo)
            put(COL_PASSWORD, password)
        }

        //insertar datos en la tabla
        val resultado = db.insert(TABLE_USUARIOS, null, datos)

        //cerrar conexion con la bd
        db.close()

        //comparar el resultado de la insercion y regresarlo como boolean
        return resultado != -1L

    }
}