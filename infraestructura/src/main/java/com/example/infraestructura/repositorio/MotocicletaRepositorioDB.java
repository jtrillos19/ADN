package com.example.infraestructura.repositorio;

import com.example.domain.entidad.Motocicleta;
import com.example.domain.repositorio.MotocicletaRepositorio;

import java.util.List;

public class MotocicletaRepositorioDB implements MotocicletaRepositorio {

    @Override
    public List<Motocicleta> obtenerMotocicletas() {
        return null;
    }

    @Override
    public void guardarMotocicleta(Motocicleta motocicleta) {

    }

    @Override
    public void eliminarMotocicleta(Motocicleta motocicleta) {

    }

    @Override
    public byte obtenerCantidadMotociletas() {
        return 0;
    }
}
