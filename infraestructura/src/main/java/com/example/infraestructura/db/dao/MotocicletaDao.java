package com.example.infraestructura.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.infraestructura.db.entidad.MotocicletaEntidad;

import java.util.List;

@Dao
public interface MotocicletaDao {

    @Insert
    void agregarMotocicleta(MotocicletaEntidad motocicletaEntidad);

    @Query("SELECT * FROM motocicleta_entidad")
    List<MotocicletaEntidad> obtenerMotocicletas();

    @Delete
    void eliminarMotocicleta(MotocicletaEntidad motocicletaEntidad);

    @Query("SELECT COUNT(*) FROM motocicleta_entidad")
    byte obtenerCantidadMotocicletas();
}
