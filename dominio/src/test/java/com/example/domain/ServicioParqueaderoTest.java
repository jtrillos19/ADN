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

    private ServicioParqueadero servicioParqueadero;

    private String excepcionSinCupoMsj;

    @Before
    public void inicializarVariables() {
        carroRepositorio = Mockito.mock(CarroRepositorio.class);
        motocicletaRepositorio = Mockito.mock(MotocicletaRepositorio.class);
        servicioParqueadero = new ServicioParqueadero(carroRepositorio, motocicletaRepositorio);
    }

    private void iniciarExcepcion(){
        excepcionSinCupoMsj = "No hay cupo disponible";
    }

    @Test
    public void validarPlacaExitosa() {
        //Arrange
        String placa = "AJT-S97";
        int diaViernes = 5;
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
        int diaLunes = 1;
        boolean resultadoEsperado;
        //Act
        resultadoEsperado = servicioParqueadero.validarPlaca(placa, diaLunes);
        //Asset
        assertEquals(true, resultadoEsperado);
    }

    @Test
    public void guardarCarroSinCupo() {
        //Arrange
        Carro carro = new Carro("AQW-5R4");
        iniciarExcepcion();
        when(carroRepositorio.obtenerCantidadCarros()).thenReturn((byte) carro.CANTIDAD_MAXIMA_EN_PARQUEADERO);
        //Act
        try {
            servicioParqueadero.guardarCarros(carro);
            fail();
        } catch (SinCupoExcepcion sinCupoExcepcion) {
            //Assert
            assertEquals(excepcionSinCupoMsj, sinCupoExcepcion.getMessage());
        }
    }

    @Test
    public void guardarMotocicletaSinCupo() {
        //Arrange
        Motocicleta motocicleta = new Motocicleta("AQW-5R4", 650);
        iniciarExcepcion();
        when(motocicletaRepositorio.obtenerCantidadMotociletas()).thenReturn((byte) motocicleta.CANTIDAD_MAXIMA_EN_PARQUEADERO);
        //Act
        try {
            servicioParqueadero.guardarMotocicletas(motocicleta);
            fail();
        } catch (SinCupoExcepcion sinCupoExcepcion) {
            //Assert
            assertEquals(excepcionSinCupoMsj, sinCupoExcepcion.getMessage());
        }
    }


}