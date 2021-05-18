package com.example.domain;

import com.example.domain.entidad.Carro;
import com.example.domain.entidad.Motocicleta;
import com.example.domain.excepcion.SinCupoExcepcion;
import com.example.domain.repositorio.CarroRepositorio;
import com.example.domain.repositorio.MotocicletaRepositorio;
import com.example.domain.servicio.ParqueaderoServicio;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


import java.util.Calendar;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Mock
    CarroRepositorio carroRepositorio = Mockito.mock(CarroRepositorio.class);

    @Mock
    MotocicletaRepositorio motocicletaRepositorio = Mockito.mock(MotocicletaRepositorio.class);

    @Mock
    ParqueaderoServicio parqueaderoServicio = Mockito.mock(ParqueaderoServicio.class);

    @Test
    public void validarPlacaExitosa() {
        //Arrange
        String placa = "AJT-S97";
        int diaActual = Calendar.FRIDAY;
        boolean resultadoEsperado;
        //Act
        resultadoEsperado = parqueaderoServicio.validarPlaca(placa, diaActual);
        //Asset
        assertFalse(resultadoEsperado);
    }

    @Test
    public void validarPlacaFallida() {
        //Arrange
        String placa = "AJT-S97";
        int diaActual = Calendar.MONDAY;
        boolean resultadoEsperado;
        //Act
        resultadoEsperado = parqueaderoServicio.validarPlaca(placa, diaActual);
        //Asset
        assertFalse(resultadoEsperado);
    }

    @Test
    public void guardarCarroSinCupo() {
        //Arrange
        Carro carro = new Carro("AQW-5D5", "carro");
        when(carroRepositorio.obtenerCantidadCarros()).thenReturn((byte) 20);
        String excepcionMsj = "No hay cupo disponible";
        //Act
        try {
            parqueaderoServicio.guardarCarros(carro);
        } catch (SinCupoExcepcion sinCupoExcepcion) {
            //Assert
            assertEquals(excepcionMsj, sinCupoExcepcion.getMessage());
        }
    }

    @Test
    public void guardarMotocicletaSinCupo() {
        //Arrange
        Motocicleta motocicleta = new Motocicleta("AQW-5D5", "motocicleta", (short) 650);
        when(motocicletaRepositorio.obtenerCantidadMotociletas()).thenReturn((byte) 10);
        String exceptionMsj = "No hay cupo disponible";
        //Act
        try {
            parqueaderoServicio.guardarMotocicletas(motocicleta);
        } catch (SinCupoExcepcion sinCupoExcepcion) {
            //Assert
            assertEquals(exceptionMsj, sinCupoExcepcion.getMessage());
        }
    }


}