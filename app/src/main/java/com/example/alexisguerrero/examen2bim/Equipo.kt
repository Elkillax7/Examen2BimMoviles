package com.example.alexisguerrero.examen2bim

import android.os.Parcel
import android.os.Parcelable

class Equipo (var id: Int,
              var nombre: String,
              var liga: String,
              var fechaCreacion: String,
              var numeroCopasInternacionales: Int,
              var campeonActual: Int,
              var createdAt: Long,
              var updatedAt: Long
              ) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readLong(),
            parcel.readLong()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(liga)
        parcel.writeString(fechaCreacion)
        parcel.writeInt(numeroCopasInternacionales)
        parcel.writeInt(campeonActual)
        parcel.writeLong(createdAt)
        parcel.writeLong(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Equipo> {
        override fun createFromParcel(parcel: Parcel): Equipo {
            return Equipo(parcel)
        }

        override fun newArray(size: Int): Array<Equipo?> {
            return arrayOfNulls(size)
        }
    }


}