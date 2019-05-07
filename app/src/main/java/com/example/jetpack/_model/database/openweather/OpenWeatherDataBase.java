package com.example.jetpack._model.database.openweather;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.jetpack._model.database.openweather.clima.ClimaDao;
import com.example.jetpack._model.database.openweather.clima.ClimaEntity;

@Database(entities = {ClimaEntity.class}, version = 1)
public abstract class OpenWeatherDataBase extends RoomDatabase {

    public abstract ClimaDao climaDao();

    private static OpenWeatherDataBase INSTANCE;

    public static OpenWeatherDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (OpenWeatherDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            OpenWeatherDataBase.class, "open_weather_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
