package com.example.domain;

import com.example.domain.entidad.Carro;
import com.example.domain.entidad.Motocicleta;
import com.example.domain.excepcion.SinCupoExcepcion;
import com.example.domain.repositorio.CarroRepositorio;
import com.example.domain.repositorio.MotocicletaRepositorio;
import com.example.domain.servicio.parqueadero.CalculadoraPreciosParqueadero;
import com.example.domain.servicio.parqueadero.ServicioParqueadero;

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
public class ExampleUnitTest {

    @Mock
    CarroRepositorio carroRepositorio;

    @Mock
    MotocicletaRepositorio motocicletaRepositorio;

    @Mock
    ServicioParqueadero servicioParqueadero;

    Carro carro;
    Motocicleta motocicleta;
    String excepcionMsj;
    Calendar fechaEntrada;
    Calendar fechaSalida;

    @Before
    public void inicializar() {
        carroRepositorio = Mockito.mock(CarroRepositorio.class);
        motocicletaRepositorio = Mockito.mock(MotocicletaRepositorio.class);

        servicioParqueadero = new ServicioParqueadero(carroRepositorio, motocicletaRepositorio);
        carro = new Carro("AQW-578", "carro");
        motocicleta = new Motocicleta("AQW-414", "motocicleta", (short) 650);
        excepcionMsj = "No hay cupo disponible";
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
    public void validarPlacaExitosa() {
        //Arrange
        String placa = "AJT-S97";
        int diaViernes = Calendar.FRIDAY;
        boolean resultadoEsperado;
        //Act
        resultadoEsperado = servicioParqueadero.validarPlaca(placa, diaViernes);
        //Asset
        assertFalse(resultadoEsperado);
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
        assertTrue(resultadoEsperado);
    }

    @Test
    public void guardarCarroSinCupo() {
        //Arrange
        when(carroRepositorio.obtenerCantidadCarros()).thenReturn((byte) 20);
        //Act
        try {
            servicioParqueadero.guardarCarros(carro);
        } catch (SinCupoExcepcion sinCupoExcepcion) {
            //Assert
            assertEquals(excepcionMsj, sinCupoExcepcion.getMessage());
        }
    }

    @Test
    public void guardarMotocicletaSinCupo() {
        //Arrange
        when(motocicletaRepositorio.obtenerCantidadMotociletas()).thenReturn((byte) 10);
        //Act
        try {
            servicioParqueadero.guardarMotocicletas(motocicleta);
        } catch (SinCupoExcepcion sinCupoExcepcion) {
            //Assert
            assertEquals(excepcionMsj, sinCupoExcepcion.getMessage());
        }
    }

    @Test
    public void calcularHorasDeParqueo(){
        //Act
        int subTotal = CalculadoraPreciosParqueadero.calcularHorasDeParqueo(fechaEntrada,fechaSalida);
        //Assert
        assertEquals(5,subTotal);
    }

    @Test
    public void valorSubTotalParqueoVehiculoExitoso(){
        //Act
        int subTotal = CalculadoraPreciosParqueadero.valorSubTotalParqueoVehiculo(fechaEntrada,fechaSalida,carro.obtenerTipo());
        //Assert
        assertEquals(5000,subTotal);
    }


}