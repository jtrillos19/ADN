package com.example.domain.motocicleta.entidad;

import com.example.domain.vehiculo.entidad.Vehiculo;

public class Motocicleta extends Vehiculo {

    private  int cilindraje;
    public  final short VALOR_HORA_PARQUEADERO = 500;
    public  final short VALOR_DIA_PARQUEADERO = 4000;
    public final short CANTIDAD_MAXIMA_EN_PARQUEADERO = 10;

    public Motocicleta(String placa, int cilindraje) {
        super(placa);
        this.cilindraje = cilindraje;
    }

    public int obtenerCilindraje() {
        return cilindraje;
    }


}
