package com.example.infraestructura.motocicleta.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.infraestructura.motocicleta.db.entidad.MotocicletaEntidad;

import java.util.List;

@Dao
public interface MotocicletaDao {

    @Insert
    void agregarMotocicleta(MotocicletaEntidad motocicletaEntidad);

    @Query("SELECT * FROM motocicleta_entidad")
    List<MotocicletaEntidad> obtenerMotocicletas();

    @Query("DELETE FROM motocicleta_entidad WHERE placa = :placaMotocicleta")
    void eliminarMotocicleta(String placaMotocicleta);

    @Query("SELECT COUNT(*) FROM motocicleta_entidad")
    byte obtenerCantidadMotocicletas();
}
