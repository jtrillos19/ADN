package com.example.domain.carro.repositorio;

import com.example.domain.carro.entidad.Carro;

import java.util.List;

public interface CarroRepositorio {

    List<Carro> obtenerCarros();

    void guardarCarro(Carro carro);

    void eliminarCarro(Carro carro);

    byte obtenerCantidadCarros();
}
