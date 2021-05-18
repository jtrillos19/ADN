package com.example.domain.excepcion;

public class PlacaNoPermitidaExcepcion extends RuntimeException {

    public static final String PLACA_NO_PERMITIDA_MSJ = "NO ESTÁ AUTORIZADO A INGRESAR";

    public PlacaNoPermitidaExcepcion(){
        super(PLACA_NO_PERMITIDA_MSJ);
    }
}
