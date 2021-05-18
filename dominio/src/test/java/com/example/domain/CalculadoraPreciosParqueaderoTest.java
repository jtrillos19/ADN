package com.example.domain;

import com.example.domain.entidad.Carro;
import com.example.domain.servicio.parqueadero.CalculadoraPreciosParqueadero;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CalculadoraPreciosParqueaderoTest {

    Carro carro;
    Calendar fechaEntrada;
    Calendar fechaSalida;

    @Before
    public void inicializarVariables() throws ParseException {
        carro = new Carro("AQW-5R4", "carro");
        inicializarFechas();
    }

    @Before
    public void inicializarFechas() throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        //Fecha de entrada
        fechaEntrada = Calendar.getInstance();
        String fechaEntradaCadena = "18-05-2021 1:20:54";
        Date fechaTemporalEntrada = formatoFecha.parse(fechaEntradaCadena);
        fechaEntrada.setTime(fechaTemporalEntrada);
        //Fecha de salida
        fechaSalida = Calendar.getInstance();
        String fechaSalidaCadena = "18-05-2021 5:30:54";
        Date fechaTemporalSalida = formatoFecha.parse(fechaSalidaCadena);
        fechaSalida.setTime(fechaTemporalSalida);
    }

    @Test
    public void calcularHorasDeParqueo() {
        //Act
        int subTotal = CalculadoraPreciosParqueadero.calcularHorasDeParqueo(fechaEntrada, fechaSalida);
        //Assert
        assertEquals(5, subTotal);
    }

    @Test
    public void valorSubTotalParqueoVehiculoExitoso() {
        //Act
        int subTotal = CalculadoraPreciosParqueadero.valorSubTotalParqueoVehiculo(fechaEntrada, fechaSalida, carro.obtenerTipo());
        //Assert
        assertEquals(5000, subTotal);
    }
}
