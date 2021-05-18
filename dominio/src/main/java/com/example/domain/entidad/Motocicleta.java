package com.example.domain.entidad;

public class Motocicleta extends Vehiculo {

    private final short cilindraje;

    public Motocicleta(String placa, String tipo, short cilindraje) {
        super(placa, tipo);
        this.cilindraje = cilindraje;
    }

    public short obtenerCilindraje() {
        return cilindraje;
    }

}
