package com.example.domain.entidad;

import java.util.Calendar;

public class Carro extends Vehiculo {

    public  final short VALOR_HORA_PARQUEADERO = 1000;
    public  final short VALOR_DIA_PARQUEADERO = 8000;
    public  final short CANTIDAD_MAXIMA_EN_PARQUEADERO = 20;

    public Carro(String placa) {
        super(placa);
    }

}
