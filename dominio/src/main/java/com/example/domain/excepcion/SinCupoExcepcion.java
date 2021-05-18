package com.example.domain.excepcion;

public class SinCupoExcepcion extends RuntimeException{

    private static final String SIN_CUPO_MJS = "No hay cupo disponible";

    public SinCupoExcepcion(){
        super(SIN_CUPO_MJS);
    }
}
