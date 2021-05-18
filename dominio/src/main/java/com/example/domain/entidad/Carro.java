package com.example.domain.entidad;

import java.util.Calendar;

public class Carro extends Vehiculo {

    public final short VALOR_HORA_PARQUEADERO = 1000;
    public final short VALOR_DIA_PARQUEADERO = 8000;
    public final short CANTIDAD_MAXIMA_EN_PARQUEADERO = 20;

    public Carro(String placa) {
        super(placa);
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
        return valorTotalPagar;
    }
}
