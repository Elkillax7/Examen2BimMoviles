package com.example.alexisguerrero.examen2bim

import android.os.Parcel
import android.os.Parcelable

class Jugador(var id: Int,
              var numeroCamiseta: Int,
              var nombreCamiseta: String,
              var nombreCompletoJugador: String,
              var poderEspecialDos: String,
              var fechaIngresoEquipo: String,
              var goles: Int,
              var equipoFutbolId: Int,
              var fotoJugador: String,
              var createdAt: Long,
              var updatedAt: Long

              ) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readLong()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(numeroCamiseta)
        parcel.writeString(nombreCamiseta)
        parcel.writeString(nombreCompletoJugador)
        parcel.writeString(poderEspecialDos)
        parcel.writeString(fechaIngresoEquipo)
        parcel.writeInt(goles)
        parcel.writeInt(equipoFutbolId)
        parcel.writeString(fotoJugador)
        parcel.writeLong(createdAt)
        parcel.writeLong(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Jugador> {
        override fun createFromParcel(parcel: Parcel): Jugador {
            return Jugador(parcel)
        }

        override fun newArray(size: Int): Array<Jugador?> {
            return arrayOfNulls(size)
        }
    }
}