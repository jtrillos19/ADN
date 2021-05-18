package com.example.domain.servicio.parqueadero;

import com.example.domain.entidad.Carro;
import com.example.domain.entidad.Motocicleta;
import com.example.domain.entidad.Vehiculo;
import com.example.domain.excepcion.PlacaNoPermitidaExcepcion;
import com.example.domain.excepcion.SinCupoExcepcion;
import com.example.domain.repositorio.CarroRepositorio;
import com.example.domain.repositorio.MotocicletaRepositorio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ServicioParqueadero {

    private final CarroRepositorio carroRepositorio;
    private final MotocicletaRepositorio motocicletaRepositorio;
    private final String LETRA_INICIAL_PLACA = "A";
    private final int DIA_DE_PARQUEO = 3;

    public ServicioParqueadero(CarroRepositorio carroRepositorio, MotocicletaRepositorio motocicletaRepositorio) {
        this.carroRepositorio = carroRepositorio;
        this.motocicletaRepositorio = motocicletaRepositorio;
    }

    public List<Vehiculo> obtenerVehiculos() {
        List<Vehiculo> vehiculoLista = new ArrayList<>();
        vehiculoLista.addAll(carroRepositorio.obtenerCarros());
        vehiculoLista.addAll(motocicletaRepositorio.obtenerMotocicletas());
        return vehiculoLista;
    }

    public void guardarCarros(Carro carro) {
        byte cantidadCarros = carroRepositorio.obtenerCantidadCarros();
        int diaActual = Calendar.getInstance().getFirstDayOfWeek();
        if (cantidadCarros == carro.CANTIDAD_MAXIMA_EN_PARQUEADERO) {
            throw new SinCupoExcepcion();
        } else if (validarPlaca(carro.obtenerPlaca(), diaActual)) {
            throw new PlacaNoPermitidaExcepcion();
        } else {
            carroRepositorio.guardarCarro(carro);
        }
    }

    public void guardarMotocicletas(Motocicleta motocicleta) {
        byte cantidadMotociletas = motocicletaRepositorio.obtenerCantidadMotociletas();
        int diaActual = Calendar.getInstance().getFirstDayOfWeek();
        if (cantidadMotociletas == motocicleta.CANTIDAD_MAXIMA_EN_PARQUEADERO) {
            throw new SinCupoExcepcion();
        } else if (validarPlaca(motocicleta.obtenerPlaca(), diaActual)) {
            throw new PlacaNoPermitidaExcepcion();
        } else {
            motocicletaRepositorio.guardarMotocicleta(motocicleta);
        }
    }

    public void eliminarCarro(Carro carro) {
        carroRepositorio.eliminarCarro(carro);
    }

    public void eliminarMotocicleta(Motocicleta motocicleta) {
        motocicletaRepositorio.eliminarMotocicleta(motocicleta);
    }

    public boolean validarPlaca(String placa, int diaActual) {
        return (placa.startsWith(LETRA_INICIAL_PLACA) && (diaActual < DIA_DE_PARQUEO));
    }

    public int valorTotalParqueaderoMotocicleta(Motocicleta motocicleta) {
        return motocicleta.calcularValorTotalDeParqueadero(Calendar.getInstance());
    }

    public int valorTotalParqueaderoCarro(Carro carro) {
        return carro.calcularValorTotalDeParqueadero(Calendar.getInstance());
    }
}
