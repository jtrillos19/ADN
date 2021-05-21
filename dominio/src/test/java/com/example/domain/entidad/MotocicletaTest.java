package com.example.domain.entidad;

import com.example.domain.motocicleta.entidad.Motocicleta;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class MotocicletaTest {
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
    public void calcularValorTotalDeParqueadero_motoCon5HorasDeParqueoCilindraje650_exitoso() {
        //Arrange
        Motocicleta motocicleta = new Motocicleta("ASW-KJ8", 650);
        motocicleta.modificarFechaIngreso(fechaIngreso);
        //Act
        int subTotal = motocicleta.calcularValorTotalDeParqueadero(fechaSalida,motocicleta.VALOR_HORA_PARQUEADERO,motocicleta.VALOR_DIA_PARQUEADERO,motocicleta.obtenerCilindraje());
        //Assert
        assertEquals(4500, subTotal);
    }
}
