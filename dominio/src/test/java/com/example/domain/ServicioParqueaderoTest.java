package com.example.domain;

import com.example.domain.entidad.Carro;
import com.example.domain.entidad.Motocicleta;
import com.example.domain.excepcion.SinCupoExcepcion;
import com.example.domain.repositorio.CarroRepositorio;
import com.example.domain.repositorio.MotocicletaRepositorio;
import com.example.domain.servicio.parqueadero.ServicioParqueadero;

import org.junit.Before;
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
public class ServicioParqueaderoTest {

    @Mock
    private CarroRepositorio carroRepositorio;

    @Mock
    private MotocicletaRepositorio motocicletaRepositorio;

    @Mock
    private ServicioParqueadero servicioParqueadero;

    private Carro carro;
    private Motocicleta motocicleta;
    private String excepcionSinCupoMsj;

    @Before
    public void inicializarVariables() {
        carroRepositorio = Mockito.mock(CarroRepositorio.class);
        motocicletaRepositorio = Mockito.mock(MotocicletaRepositorio.class);

        servicioParqueadero = new ServicioParqueadero(carroRepositorio, motocicletaRepositorio);
        carro = new Carro("AQW-578");
        motocicleta = new Motocicleta("AQW-414", (short) 650);
        excepcionSinCupoMsj = "No hay cupo disponible";
    }

    @Test
    public void validarPlacaExitosa() {
        //Arrange
        String placa = "AJT-S97";
        int diaViernes = Calendar.FRIDAY;
        boolean resultadoEsperado;
        //Act
        resultadoEsperado = servicioParqueadero.validarPlaca(placa, diaViernes);
        //Asset
        assertEquals(false, resultadoEsperado);
    }

    @Test
    public void validarPlacaFallida() {
        //Arrange
        String placa = "AJT-S97";
        int diaLunes = Calendar.MONDAY;
        boolean resultadoEsperado;
        //Act
        resultadoEsperado = servicioParqueadero.validarPlaca(placa, diaLunes);
        //Asset
        assertEquals(true, resultadoEsperado);
    }

    @Test
    public void guardarCarroSinCupo() {
        //Arrange
        when(carroRepositorio.obtenerCantidadCarros()).thenReturn((byte) carro.CANTIDAD_MAXIMA_EN_PARQUEADERO);
        //Act
        try {
            servicioParqueadero.guardarCarros(carro);
        } catch (SinCupoExcepcion sinCupoExcepcion) {
            //Assert
            assertEquals(excepcionSinCupoMsj, sinCupoExcepcion.getMessage());
        }
    }

    @Test
    public void guardarMotocicletaSinCupo() {
        //Arrange
        when(motocicletaRepositorio.obtenerCantidadMotociletas()).thenReturn((byte) motocicleta.CANTIDAD_MAXIMA_EN_PARQUEADERO);
        //Act
        try {
            servicioParqueadero.guardarMotocicletas(motocicleta);
        } catch (SinCupoExcepcion sinCupoExcepcion) {
            //Assert
            assertEquals(excepcionSinCupoMsj, sinCupoExcepcion.getMessage());
        }
    }


}