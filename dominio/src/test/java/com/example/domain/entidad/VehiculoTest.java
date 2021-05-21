package com.example.domain.entidad;

import com.example.domain.vehiculo.entidad.Vehiculo;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class VehiculoTest {


    @Test
    public void calcularTotalHorasEnParqueadero_diferancia5Horas_exitoso() throws ParseException {
        //Arrange
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        //Fecha de entrada
        Calendar fechaIngreso = Calendar.getInstance();
        String fechaIngresoFormato = "19-05-2021 6:20:56";
        Date fechaIngresoTemporal = formatoFecha.parse(fechaIngresoFormato);
        fechaIngreso.setTime(fechaIngresoTemporal);
        //Fecha de salida
        Calendar fechaSalida = Calendar.getInstance();
        String fechaSalidaFormato = "19-05-2021 10:30:56";
        Date fechaSalidaTemporal = formatoFecha.parse(fechaSalidaFormato);
        fechaSalida.setTime(fechaSalidaTemporal);
        Vehiculo vehiculo = new Vehiculo("AQS-JU8");
        vehiculo.modificarFechaIngreso(fechaIngreso);
        //Act
        int totalHoras = vehiculo.calcularTotalHorasEnParqueadero(fechaSalida);
        //Assert
        assertEquals(5, totalHoras);
    }
}
