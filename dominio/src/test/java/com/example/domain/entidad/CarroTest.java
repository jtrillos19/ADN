package com.example.domain.entidad;

import com.example.domain.carro.entidad.Carro;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CarroTest {

    private SimpleDateFormat formatoFecha;

    @Before
    public void iniciarFormatoFecha() {
        formatoFecha = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    }

    private Calendar fechaEntrada(String fechaEntrada) throws ParseException {
        Calendar fechaIngreso = Calendar.getInstance();
        Date fechaIngresoTemporal = formatoFecha.parse(fechaEntrada);
        fechaIngreso.setTime(fechaIngresoTemporal);
        return fechaIngreso;
    }

    private Calendar fechaSalida(String fechaSalida) throws ParseException {
        Calendar fechaIngreso = Calendar.getInstance();
        Date fechaIngresoTemporal = formatoFecha.parse(fechaSalida);
        fechaIngreso.setTime(fechaIngresoTemporal);
        return fechaIngreso;
    }

    @Test
    public void calcularValorTotalDeParqueadero_carroCon5HorasDeParqueo_exitoso() throws ParseException {
        //Arrange
        Carro carro = new Carro("ASW-KJ8");
        carro.modificarFechaIngreso(fechaEntrada("19-05-2021 6:20:56"));
        //Act
        int subTotal = carro.calcularValorTotalDeParqueadero(fechaSalida("19-05-2021 10:30:56"), carro.VALOR_HORA_PARQUEADERO, carro.VALOR_DIA_PARQUEADERO);
        //Assert
        assertEquals(5000, subTotal);
    }

    @Test
    public void calcularValorTotalDeParqueadero_24horasDeParqueo_exitoso() throws ParseException {
        //Arrange
        Carro carro = new Carro("ASW-KJ8");
        carro.modificarFechaIngreso(fechaEntrada("19-05-2021 10:30:56"));
        //Act
        int subTotal = carro.calcularValorTotalDeParqueadero(fechaSalida("20-05-2021 10:30:56"), carro.VALOR_HORA_PARQUEADERO, carro.VALOR_DIA_PARQUEADERO);
        //Assert
        assertEquals(8000, subTotal);
    }

    @Test
    public void calcularValorTotalDeParqueadero_34horasDeParqueo_exitoso() throws ParseException {
        //Arrange
        Carro carro = new Carro("ASW-KJ8");
        carro.modificarFechaIngreso(fechaEntrada("19-05-2021 1:30:56"));
        //Act
        int subTotal = carro.calcularValorTotalDeParqueadero(fechaSalida("20-05-2021 11:30:56"), carro.VALOR_HORA_PARQUEADERO, carro.VALOR_DIA_PARQUEADERO);
        //Assert
        assertEquals(16000, subTotal);
    }
}
