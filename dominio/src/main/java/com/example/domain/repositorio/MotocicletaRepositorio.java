package com.example.domain.repositorio;

import com.example.domain.entidad.Motocicleta;

import java.util.List;

public interface MotocicletaRepositorio {

    List<Motocicleta> obtenerMotocicletas();

    void guardarMotocicleta(Motocicleta motocicleta);

    void eliminarMotocicleta(Motocicleta motocicleta);

    byte obtenerCantidadMotociletas();
}
