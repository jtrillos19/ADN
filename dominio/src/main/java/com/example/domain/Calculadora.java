package com.example.domain;

import java.util.Calendar;

public class Calculadora {

    public static int calcularHorasDeParqueo(Calendar fechaEntrada, Calendar fechaSalida) {
        float horaEnMillisegundos = 3600000;
        long diferenciaFechas = fechaSalida.getTimeInMillis() - fechaEntrada.getTimeInMillis();
        return (int) Math.ceil(diferenciaFechas / horaEnMillisegundos);
    }

    public static int valorSubTotalParqueoVehiculo(Calendar fechaEntrada, Calendar fechaSalida, String tipoVehiculo) {
        int horasEnParqueadero = calcularHorasDeParqueo(fechaEntrada, fechaSalida);
        int valorHoraVehiculo = Contantes.HORA_MOTOCICLETA;
        int valorDiaVehiculo = Contantes.DIA_MOTOCICLETA;
        if (tipoVehiculo.equals("carro")) {
            valorHoraVehiculo = Contantes.HORA_CARRO;
            valorDiaVehiculo = Contantes.DIA_CARRO;
        }
        int valorSubTotal = 0;
        if (horasEnParqueadero < 9) {
            valorSubTotal = valorHoraVehiculo * horasEnParqueadero;
        } else if (horasEnParqueadero < 25) {
            valorSubTotal = valorDiaVehiculo;
        } else {
            int diasEnParqueadero = horasEnParqueadero % 24;
            if (diasEnParqueadero == 0) {
                valorSubTotal = (horasEnParqueadero / 24) * valorDiaVehiculo;
            } else if (diasEnParqueadero < 9) {
                valorSubTotal += Math.floor(horasEnParqueadero / 24f) * valorDiaVehiculo;
                valorSubTotal += diasEnParqueadero * valorHoraVehiculo;
            } else {
                valorSubTotal = (int) Math.ceil(horasEnParqueadero / 24f) * valorDiaVehiculo;
            }
        }
        return valorSubTotal;
    }
}
