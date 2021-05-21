package com.example.infraestructura.carro.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.infraestructura.carro.db.entidad.CarroEntidad;

import java.util.List;


@Dao
public interface CarroDao {

    @Insert
    void agregarCarro(CarroEntidad carroEntidad);

    @Query("SELECT * FROM carro_entidad")
    List<CarroEntidad> obtenerCarros();

    @Query("DELETE FROM carro_entidad WHERE placa = :placaCarro")
    void eliminarCarro(String placaCarro);

    @Query("SELECT COUNT(*) FROM carro_entidad")
    byte obtenerCantidadCarros();
}
