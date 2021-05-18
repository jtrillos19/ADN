package com.example.domain.entidad;

public class Motocicleta extends Vehiculo {

    private short cilindraje;

    public Motocicleta(String placa, String tipo, short cilindraje) {
        super(placa, tipo);
        setCilindraje(cilindraje);
    }

    public short getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(short cilindraje) {
        this.cilindraje = cilindraje;
    }
}
