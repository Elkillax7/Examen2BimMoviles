package com.example.alexisguerrero.examen2bim

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    lateinit var usuarios: ArrayList<Usuario>
    var estadoIngresoSistema = 0
    lateinit var valorRol:String
    lateinit var usuarioActual:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        }

}



