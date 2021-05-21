package com.example.domain.vehiculo.excepcion;

public class PlacaNoPermitidaExcepcion extends RuntimeException {

    private static final String PLACA_NO_PERMITIDA_MSJ = "No está autorizado a ingresar";

    public PlacaNoPermitidaExcepcion(){
        super(PLACA_NO_PERMITIDA_MSJ);
    }
}
