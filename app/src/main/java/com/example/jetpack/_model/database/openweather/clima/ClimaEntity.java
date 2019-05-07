package com.example.jetpack._model.database.openweather.clima;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "CLIMA_ENTITY")
public class ClimaEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id = 1;

    @ColumnInfo(name = "latitud")
    private double latitud;

    @ColumnInfo(name = "longitud")
    private double longitud;

    @ColumnInfo(name = "temperatura")
    private float temperatura;

    @ColumnInfo(name = "humedad")
    private float humedad;

    @ColumnInfo(name = "presion")
    private float presion;

    @ColumnInfo(name = "descripcionClima")
    private String descripcionClima;

    @ColumnInfo(name = "visibilidad")
    private float visibilidad;

    @ColumnInfo(name = "ultimaActualizacion")
    private Date ultimaActualizacion;

    @ColumnInfo(name = "direccionViento")
    private String direccionViento;

    @ColumnInfo(name = "velocidad")
    private int velocidad;


}
