package com.example.domain;

import com.example.domain.carro.entidad.Carro;
import com.example.domain.motocicleta.entidad.Motocicleta;
import com.example.domain.vehiculo.excepcion.PlacaNoPermitidaExcepcion;
import com.example.domain.vehiculo.excepcion.SinCupoExcepcion;
import com.example.domain.carro.repositorio.CarroRepositorio;
import com.example.domain.motocicleta.repositorio.MotocicletaRepositorio;
import com.example.domain.parqueadero.servicio.ParqueaderoServicio;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ParqueaderoServicioTest {

    @Mock
    private CarroRepositorio carroRepositorio;

    @Mock
    private MotocicletaRepositorio motocicletaRepositorio;

    private ParqueaderoServicio parqueaderoServicio;


    @Before
    public void inicializarVariables() {
        carroRepositorio = Mockito.mock(CarroRepositorio.class);
        motocicletaRepositorio = Mockito.mock(MotocicletaRepositorio.class);
        parqueaderoServicio = new ParqueaderoServicio(carroRepositorio, motocicletaRepositorio);
    }

    private String iniciarExcepcionSinCupo(){
        return "No hay cupo disponible";
    }

    private Calendar fechaEntrada() throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Calendar fechaIngreso = Calendar.getInstance();
        Date fechaIngresoTemporal = formatoFecha.parse("08-03-2021 6:20:56");
        fechaIngreso.setTime(fechaIngresoTemporal);
        return fechaIngreso;
    }

    @Test
    public void validarPlaca_placaIniciadaEnAyElDiaViernes_exitoso() {
        //Arrange
        String placa = "ASD-J87";
        int diaViernes = 5;
        boolean resultadoEsperado;
        //Act
        resultadoEsperado = parqueaderoServicio.validarPlaca(placa, diaViernes);
        //Asset
        assertEquals(false, resultadoEsperado);
    }

    @Test
    public void validarPlaca_placaIniciadaEnAyElDiaLunes_exitoso() {
        //Arrange
        String placa = "ASD-J87";
        int diaLunes = 1;
        boolean resultadoEsperado;
        //Act
        resultadoEsperado = parqueaderoServicio.validarPlaca(placa, diaLunes);
        //Asset
        assertEquals(true, resultadoEsperado);

    }

    @Test
    public void guardarCarro_sinCupoParaIngresar_exitoso() {
        //Arrange
        Carro carro = new Carro("AQW-5R4");
        when(carroRepositorio.obtenerCantidadCarros()).thenReturn((byte) carro.CANTIDAD_MAXIMA_EN_PARQUEADERO);
        //Act
        try {
            parqueaderoServicio.guardarCarros(carro);
        } catch (SinCupoExcepcion excepcion) {
            //Assert
            assertEquals(iniciarExcepcionSinCupo(), excepcion.getMessage());
        }
    }

    @Test
    public void guardarCarro_placaNoAutorizaDiaLunes_exitoso() throws ParseException {
        //Arrange
        Carro carro = new Carro("AQW-5R4");
        carro.modificarFechaIngreso(fechaEntrada());
        String excepcionPlacaNoPermitida = "No está autorizado a ingresar";
        when(carroRepositorio.obtenerCantidadCarros()).thenReturn((byte) 10);
        //Act
        try {
            parqueaderoServicio.guardarCarros(carro);
        } catch (PlacaNoPermitidaExcepcion excepcion) {
            //Assert
            assertEquals(excepcionPlacaNoPermitida, excepcion.getMessage());
        }
    }

    @Test
    public void guardarMoto_sinCupoParaIngresar_exitoso() {
        //Arrange
        Motocicleta motocicleta = new Motocicleta("AQW-5R4", 650);
        when(motocicletaRepositorio.obtenerCantidadMotociletas()).thenReturn((byte) motocicleta.CANTIDAD_MAXIMA_EN_PARQUEADERO);
        //Act
        try {
            parqueaderoServicio.guardarMotocicletas(motocicleta);
        } catch (SinCupoExcepcion excepcion) {
            //Assert
            assertEquals(iniciarExcepcionSinCupo(), excepcion.getMessage());
        }
    }

    @Test
    public void guardarMoto_placaNoAutorizaDiaLunes_exitoso() throws ParseException {
        //Arrange
        Motocicleta motocicleta = new Motocicleta("AQW-5R4", 300);
        motocicleta.modificarFechaIngreso(fechaEntrada());
        String excepcionPlacaNoPermitida = "No está autorizado a ingresar";
        when(motocicletaRepositorio.obtenerCantidadMotociletas()).thenReturn((byte) 5);
        //Act
        try {
            parqueaderoServicio.guardarMotocicletas(motocicleta);
        } catch (PlacaNoPermitidaExcepcion excepcion) {
            //Assert
            assertEquals(excepcionPlacaNoPermitida, excepcion.getMessage());
        }
    }


}