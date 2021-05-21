package com.example.domain.vehiculo.excepcion;

public class SinCupoExcepcion extends RuntimeException{

    private static final String SIN_CUPO_MJS = "No hay cupo disponible";

    public SinCupoExcepcion(){
        super(SIN_CUPO_MJS);
    }
}
