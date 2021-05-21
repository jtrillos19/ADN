package com.example.domain.motocicleta.repositorio;

import com.example.domain.motocicleta.entidad.Motocicleta;

import java.util.List;

public interface MotocicletaRepositorio {

    List<Motocicleta> obtenerMotocicletas();

    void guardarMotocicleta(Motocicleta motocicleta);

    void eliminarMotocicleta(Motocicleta motocicleta);

    byte obtenerCantidadMotociletas();
}
