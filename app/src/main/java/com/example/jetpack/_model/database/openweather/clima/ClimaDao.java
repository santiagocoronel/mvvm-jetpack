package com.example.jetpack._model.database.openweather.clima;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface ClimaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ClimaEntity climaEntity);

    @Delete
    void delete(ClimaEntity setting);

    @Query("SELECT * FROM CLIMA_ENTITY WHERE CLIMA_ENTITY.id = 1")
    LiveData<ClimaEntity> get();
}
