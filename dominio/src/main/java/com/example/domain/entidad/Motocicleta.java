package com.example.domain.entidad;

import java.util.Calendar;

public class Motocicleta extends Vehiculo {

    private final int cilindraje;
    public static final short VALOR_HORA_PARQUEADERO = 500;
    public static final short VALOR_DIA_PARQUEADERO = 4000;
    public static final short CILINDRAJE_MAXIMO = 500;
    public static final short EXCEDENTE = 2000;
    public final short CANTIDAD_MAXIMA_EN_PARQUEADERO = 10;

    public Motocicleta(String placa, int cilindraje) {
        super(placa);
        this.cilindraje = cilindraje;
    }

    public int obtenerCilindraje() {
        return cilindraje;
    }

    public int calcularValorTotalDeParqueadero(Calendar fechaSalida) {
        int totalHorasEnParqueadero = calcularTotalHorasEnParqueadero(fechaSalida);
        int valorTotalPagar = 0;
        if (totalHorasEnParqueadero < LIMITE_MAXIMO_HORAS) {
            valorTotalPagar = VALOR_HORA_PARQUEADERO * totalHorasEnParqueadero;
        } else if (totalHorasEnParqueadero <= CANTIDAD_HORAS_DIA) {
            valorTotalPagar = VALOR_DIA_PARQUEADERO;
        } else {
            int diasEnParqueadero = totalHorasEnParqueadero % CANTIDAD_HORAS_DIA;
            if (diasEnParqueadero == 0) {
                valorTotalPagar = (totalHorasEnParqueadero / CANTIDAD_HORAS_DIA) * VALOR_DIA_PARQUEADERO;
            } else if (diasEnParqueadero < LIMITE_MAXIMO_HORAS) {
                valorTotalPagar += Math.floor(totalHorasEnParqueadero / ((float) CANTIDAD_HORAS_DIA)) * VALOR_DIA_PARQUEADERO;
                valorTotalPagar += diasEnParqueadero * VALOR_HORA_PARQUEADERO;
            } else {
                valorTotalPagar = (int) Math.ceil(totalHorasEnParqueadero / ((float) CANTIDAD_HORAS_DIA)) * VALOR_DIA_PARQUEADERO;
            }
        }
        if (obtenerCilindraje() > CILINDRAJE_MAXIMO) {
            valorTotalPagar += EXCEDENTE;
        }
        return valorTotalPagar;
    }

}
