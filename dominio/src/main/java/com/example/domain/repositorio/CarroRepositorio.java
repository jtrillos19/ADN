package com.example.domain.repositorio;

import com.example.domain.entidad.Carro;

import java.util.List;

public interface CarroRepositorio {

    List<Carro> obtenerCarros();

    void guardarCarro(Carro carro);

    void eliminarCarro(Carro carro);

    byte obtenerCantidadCarros();
}
