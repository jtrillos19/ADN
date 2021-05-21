package com.example.domain.vehiculo.entidad;

import java.util.Calendar;

public class Vehiculo {

    private final String placa;
    private Calendar fechaIngreso;
    private final float HORA_EN_MILISEGUNDOS = 3600000;
    protected final int LIMITE_MAXIMO_HORAS = 9;
    protected final int CANTIDAD_HORAS_DIA = 24;
    protected  final short CILINDRAJE_MAXIMO = 500;
    protected  final short EXCEDENTE = 2000;

    public Vehiculo(String placa) {
        this.placa = placa;
        modificarFechaIngreso(Calendar.getInstance());
    }

    public String obtenerPlaca() {
        return placa;
    }

    public Calendar obtenerFechaIngreso() {
        return fechaIngreso;
    }

    public void modificarFechaIngreso(Calendar fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int calcularTotalHorasEnParqueadero(Calendar fechaSalida) {
        long diferenciaEntreFechas = fechaSalida.getTimeInMillis() - obtenerFechaIngreso().getTimeInMillis();
        return (int) Math.ceil(diferenciaEntreFechas / HORA_EN_MILISEGUNDOS);
    }
    public int calcularValorTotalDeParqueadero(Calendar fechaSalida, short valorHoraParqueadero, short valorDiaParqueadero) {
        int totalHorasEnParqueadero = calcularTotalHorasEnParqueadero(fechaSalida);
        int valorTotalPagar = 0;
        if (totalHorasEnParqueadero < LIMITE_MAXIMO_HORAS) {
            valorTotalPagar = valorHoraParqueadero * totalHorasEnParqueadero;
        } else if (totalHorasEnParqueadero <= CANTIDAD_HORAS_DIA) {
            valorTotalPagar = valorDiaParqueadero;
        } else {
            int diasEnParqueadero = totalHorasEnParqueadero % CANTIDAD_HORAS_DIA;
            if (diasEnParqueadero == 0) {
                valorTotalPagar = (totalHorasEnParqueadero / CANTIDAD_HORAS_DIA) * valorDiaParqueadero;
            } else if (diasEnParqueadero < LIMITE_MAXIMO_HORAS) {
                valorTotalPagar += Math.floor(totalHorasEnParqueadero / ((float) CANTIDAD_HORAS_DIA)) * valorDiaParqueadero;
                valorTotalPagar += diasEnParqueadero * valorHoraParqueadero;
            } else {
                valorTotalPagar = (int) Math.ceil(totalHorasEnParqueadero / ((float) CANTIDAD_HORAS_DIA)) * valorDiaParqueadero;
            }
        }
        return valorTotalPagar;
    }

    public int calcularValorTotalDeParqueadero(Calendar fechaSalida, short valorHoraParqueadero, short valorDiaParqueadero, int cilindraje) {
        int totalPagar = calcularValorTotalDeParqueadero(fechaSalida, valorHoraParqueadero, valorDiaParqueadero);
        if (cilindraje > CILINDRAJE_MAXIMO) {
            totalPagar += EXCEDENTE;
        }
        return totalPagar;
    }
}
