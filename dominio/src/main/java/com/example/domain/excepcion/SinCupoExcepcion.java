package com.example.domain.excepcion;

public class SinCupoExcepcion extends RuntimeException{

    public static final String SIN_CUPO_MJS = "NO HAY CUPO DISPONIBLE";

    public SinCupoExcepcion(){
        super(SIN_CUPO_MJS);
    }
}
