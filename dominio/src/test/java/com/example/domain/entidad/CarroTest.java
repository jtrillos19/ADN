package com.example.domain.entidad;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CarroTest {

    private Calendar fechaIngreso;
    private Calendar fechaSalida;

    @Before
    public void inicializarFechas() throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        //Fecha de entrada
        fechaIngreso = Calendar.getInstance();
        String fechaIngresoFormato = "09-03-2021 6:20:56";
        Date fechaIngresoTemporal = formatoFecha.parse(fechaIngresoFormato);
        fechaIngreso.setTime(fechaIngresoTemporal);
        //Fecha de salida
        fechaSalida = Calendar.getInstance();
        String fechaSalidaFormato = "09-03-2021 10:30:56";
        Date fechaSalidaTemporal = formatoFecha.parse(fechaSalidaFormato);
        fechaSalida.setTime(fechaSalidaTemporal);
    }

    @Test
    public void calcularValorTotalDeParqueadero_carroCon5HorasDeParqueo_exitoso() {
        //Arrange
        Carro carro = new Carro("ASW-KJ8");
        carro.modificarFechaIngreso(fechaIngreso);
        //Act
        int subTotal = carro.calcularValorTotalDeParqueadero(fechaSalida);
        //Assert
        assertEquals(5000, subTotal);
    }
}
