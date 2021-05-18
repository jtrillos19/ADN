package com.example.domain.entidad;

import java.util.Calendar;

public class Vehiculo {

    private String placa;
    private String tipo;
    private Calendar fechaIngreso;

    public Vehiculo(String placa, String tipo) {
        setPlaca(placa);
        setTipo(tipo);
        setFechaIngreso(Calendar.getInstance());
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Calendar getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Calendar fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
