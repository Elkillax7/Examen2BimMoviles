package com.example.alexisguerrero.examen2bim

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut

class DatabaseJugador {

    companion object {

        fun insertJugador(jugador:Jugador){
            "http://192.168.1.14:1337/Pokemon".httpPost(listOf("numeroCamiseta" to jugador.numeroCamiseta,
                    "nombreCamiseta" to jugador.nombreCamiseta,
                    "nombreCompletoJugador" to jugador.nombreCompletoJugador,
                    "poderEspecialDos" to jugador.poderEspecialDos,
                    "fechaIngresoEquipo" to jugador.fechaIngresoEquipo,
                    "goles" to jugador.goles,
                    "fotoJugador" to jugador.fotoJugador,
                    "equipoFutbolId" to jugador.equipoFutbolId ))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun delJugador(id: Int) {
            "http://192.168.1.14:1337/Jugador/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun updateJugador(jugador: Jugador) {
            "http://192.168.1.14:1337/Jugador/${jugador.id}".httpPut(listOf("numero" to jugador.numeroCamiseta,
                    "nombreCamiseta" to jugador.nombreCamiseta,
                    "nombreCompletoJugador" to jugador.nombreCompletoJugador,
                    "poderEspecialDos" to jugador.poderEspecialDos,
                    "fechaIngresoEquipo" to jugador.fechaIngresoEquipo,
                    "goles" to jugador.goles))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getJugadores(equipoId: Int): ArrayList<Jugador> {
            val jugadores: ArrayList<Jugador> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.1.14:1337/Jugador?equipoFutbolId=$equipoId".httpGet().responseString()
            val jsonJugador = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonJugador)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val numeroCamiseta = it["numeroCamiseta"] as Int
                val nombreCamiseta = it["nombreCamiseta"] as String
                val nombreCompletoJugador = it["nombreCompletoJugador"] as String
                val poderEspecialDos = it["poderEspecialDos"] as String
                val fechaIngresoEquipo = it["fechaIngresoEquipo"] as String
                val goles = it["goles"] as Int
                val fotoJugador = it["fotoJugador"] as String
                //val latitud = it["latitud"] as Double
                // val longitud = it["longitud"] as Double
                val jugador = Jugador(id,numeroCamiseta,nombreCamiseta,nombreCompletoJugador,poderEspecialDos,fechaIngresoEquipo,goles,fotoJugador,equipoId,0,0)
                jugadores.add(jugador)
            }
            return jugadores
        }




    }
}