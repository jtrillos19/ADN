package com.example.domain.entidad;

import java.util.Calendar;

public abstract class Vehiculo {

    private final String placa;
    private final String tipo;
    private Calendar fechaIngreso;

    public Vehiculo(String placa, String tipo) {
        this.placa = placa;
        this.tipo = tipo;
        modificarFechaIngreso(Calendar.getInstance());
    }

    public String obtenerPlaca() {
        return placa;
    }

    public String obtenerTipo() {
        return tipo;
    }

    public Calendar obtenerFechaIngreso() {
        return fechaIngreso;
    }

    public void modificarFechaIngreso(Calendar fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
