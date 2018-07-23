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

class DatabaseEquipo {

    companion object {

        fun insertarEquipo(equipo:Equipo){
            "http://192.168.1.14:1337/Equipo".httpPost(listOf("nombre" to equipo.nombre,
                    "liga" to equipo.liga,
                    "fechaCreacion" to equipo.fechaCreacion,
                    "numeroCopasInternacionales" to equipo.numeroCopasInternacionales,
                    "campeonActual" to equipo.campeonActual))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun delEquipo(id: Int) {
            "http://192.168.1.14:1337/Equipo/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun updateEquipo(equipo: Equipo) {
            "http://192.168.1.14:1337/Equipo/${equipo.id}".httpPut(listOf("nombre" to equipo.nombre,
                    "liga" to equipo.liga,
                    "fechaCreacion" to equipo.fechaCreacion,
                    "numeroCopasInternacionales" to equipo.numeroCopasInternacionales,
                    "campeonActual" to equipo.campeonActual))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getList(): ArrayList<Equipo> {
            val equipos: ArrayList<Equipo> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.1.14/Equipo".httpGet().responseString()
            val jsonEquipo = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonEquipo)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombre = it["nombre"] as String
                val liga = it["liga"] as String
                val fechaCreacion = it["fechaCreacion"] as String
                val numeroCopasInternacionales = it["numeroCopasInternacionales"] as Int
                val campeonActual = it["campeonActual"] as Int
                val equipo = Equipo(id, nombre, liga, fechaCreacion, numeroCopasInternacionales, campeonActual, 0, 0)
                equipos.add(equipo)
            }
            return equipos
        }

        fun searchEquipo(nombre:String): ArrayList<Equipo> {
            val equipos: ArrayList<Equipo> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.1.14:1337/Equipo?nombre=${nombre}".httpGet().responseString()
            val jsonEquipo = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonEquipo)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombre = it["nombre"] as String
                val liga = it["liga"] as String
                val fechaCreacion = it["fechaCreacion"] as String
                val numeroCopasInternacionales = it["medallas"] as Int
                val campeonActual = it["campeonActual"] as Int
                val equipo = Equipo(id, nombre, liga, fechaCreacion, numeroCopasInternacionales, campeonActual, 0, 0)
                equipos.add(equipo)
            }
            return equipos
        }

    }
}