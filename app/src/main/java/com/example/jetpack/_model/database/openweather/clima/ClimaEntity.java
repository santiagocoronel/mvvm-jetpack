package com.example.jetpack._model.database.openweather.clima;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.jetpack._model.database._converters.StringToDateConverter;

import java.util.Date;

@Entity(tableName = "CLIMA_ENTITY")
public class ClimaEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "latitud")
    private double latitud;

    @ColumnInfo(name = "longitud")
    private double longitud;

    @ColumnInfo(name = "temperatura")
    private double temperatura;

    @ColumnInfo(name = "humedad")
    private float humedad;

    @ColumnInfo(name = "presion")
    private float presion;

    @ColumnInfo(name = "descripcionClima")
    private String descripcionClima;

    @ColumnInfo(name = "visibilidad")
    private float visibilidad;

    @ColumnInfo(name = "ultimaActualizacion")
    @TypeConverters(StringToDateConverter.class)
    private Date ultimaActualizacion;

    @ColumnInfo(name = "direccionViento")
    private String direccionViento;

    @ColumnInfo(name = "velocidad")
    private double velocidad;

    @ColumnInfo(name = "pais")
    private String pais;

    @ColumnInfo(name = "provincia")
    private String provincia;

    @ColumnInfo(name = "ciudad")
    private String ciudad;


    public ClimaEntity() {
    }

    private ClimaEntity(Builder builder) {
        this.id = builder.id;
        this.latitud = builder.latitud;
        this.longitud = builder.longitud;
        this.temperatura = builder.temperatura;
        this.humedad = builder.humedad;
        this.presion = builder.presion;
        this.descripcionClima = builder.descripcionClima;
        this.visibilidad = builder.visibilidad;
        this.ultimaActualizacion = builder.ultimaActualizacion;
        this.direccionViento = builder.direccionViento;
        this.velocidad = builder.velocidad;
        this.pais = builder.pais;
        this.provincia = builder.provincia;
        this.ciudad = builder.ciudad;
    }

    public static Builder newClimaEntity() {
        return new Builder();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public float getHumedad() {
        return humedad;
    }

    public void setHumedad(float humedad) {
        this.humedad = humedad;
    }

    public float getPresion() {
        return presion;
    }

    public void setPresion(float presion) {
        this.presion = presion;
    }

    public String getDescripcionClima() {
        return descripcionClima;
    }

    public void setDescripcionClima(String descripcionClima) {
        this.descripcionClima = descripcionClima;
    }

    public float getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(float visibilidad) {
        this.visibilidad = visibilidad;
    }

    public Date getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(Date ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public String getDireccionViento() {
        return direccionViento;
    }

    public void setDireccionViento(String direccionViento) {
        this.direccionViento = direccionViento;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }


    public static final class Builder {
        private int id;
        private double latitud;
        private double longitud;
        private double temperatura;
        private float humedad;
        private float presion;
        private String descripcionClima;
        private float visibilidad;
        private Date ultimaActualizacion;
        private String direccionViento;
        private double velocidad;
        private String pais;
        private String provincia;
        private String ciudad;

        public Builder() {
        }

        public ClimaEntity build() {
            return new ClimaEntity(this);
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder latitud(double latitud) {
            this.latitud = latitud;
            return this;
        }

        public Builder longitud(double longitud) {
            this.longitud = longitud;
            return this;
        }

        public Builder temperatura(double temperatura) {
            this.temperatura = temperatura;
            return this;
        }

        public Builder humedad(float humedad) {
            this.humedad = humedad;
            return this;
        }

        public Builder presion(float presion) {
            this.presion = presion;
            return this;
        }

        public Builder descripcionClima(String descripcionClima) {
            this.descripcionClima = descripcionClima;
            return this;
        }

        public Builder visibilidad(float visibilidad) {
            this.visibilidad = visibilidad;
            return this;
        }

        public Builder ultimaActualizacion(Date ultimaActualizacion) {
            this.ultimaActualizacion = ultimaActualizacion;
            return this;
        }

        public Builder direccionViento(String direccionViento) {
            this.direccionViento = direccionViento;
            return this;
        }

        public Builder velocidad(double velocidad) {
            this.velocidad = velocidad;
            return this;
        }

        public Builder pais(String pais) {
            this.pais = pais;
            return this;
        }

        public Builder provincia(String provincia) {
            this.provincia = provincia;
            return this;
        }

        public Builder ciudad(String ciudad) {
            this.ciudad = ciudad;
            return this;
        }
    }
}
