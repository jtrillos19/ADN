package com.example.infraestructura.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.infraestructura.db.entidad.CarroEntidad;

import java.util.List;


@Dao
public interface CarroDao {

    @Insert
    void agregarCarro(CarroEntidad carroEntidad);

    @Query("SELECT * FROM carro_entidad")
    List<CarroEntidad> obtenerCarros();

    @Delete
    void eliminarCarro(CarroEntidad carroEntidad);

    @Query("SELECT COUNT(*) FROM carro_entidad")
    byte obtenerCantidadCarros();
}
