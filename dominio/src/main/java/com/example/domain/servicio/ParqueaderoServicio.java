package com.example.domain.servicio;

import com.example.domain.Calculadora;
import com.example.domain.Contantes;
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

import javax.inject.Inject;

public class ParqueaderoServicio {

    private CarroRepositorio carroRepositorio;
    private MotocicletaRepositorio motocicletaRepositorio;

    @Inject
    public ParqueaderoServicio(CarroRepositorio carroRepositorio, MotocicletaRepositorio motocicletaRepositorio) {
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
        byte cantidadVehiculos = carroRepositorio.obtenerCantidadCarros();
        int diaActual = Calendar.getInstance().getFirstDayOfWeek();
        if (cantidadVehiculos == 20) {
            throw new SinCupoExcepcion();
        } else if (validarPlaca(carro.getPlaca(), diaActual)) {
            throw new PlacaNoPermitidaExcepcion();
        } else {
            carroRepositorio.guardarCarro(carro);
        }
    }

    public void guardarMotocicletas(Motocicleta motocicleta) {
        byte cantidadVehiculos = motocicletaRepositorio.obtenerCantidadMotociletas();
        int diaActual = Calendar.getInstance().getFirstDayOfWeek();
        if (cantidadVehiculos == 10) {
            throw new SinCupoExcepcion();
        } else if (validarPlaca(motocicleta.getPlaca(), diaActual)) {
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
        return (placa.startsWith("A") && (diaActual < 3));
    }

    public int valorTotalParqueaderoMotocicleta(Motocicleta motocicleta) {
        Calendar fechaEntrada = motocicleta.getFechaIngreso();
        Calendar fechaSalida = Calendar.getInstance();
        int valorTotal = Calculadora.valorSubTotalParqueoVehiculo(fechaEntrada, fechaSalida, motocicleta.getTipo());
        if (motocicleta.getCilindraje() > Contantes.CILINDRAJE_MOTOCICLETA) {
            valorTotal += Contantes.EXCEDENTE_MOTOCICLETA;
        }
        return valorTotal;
    }

    public int valorTotalParqueaderoCarro(Carro carro) {
        Calendar fechaEntrada = carro.getFechaIngreso();
        Calendar fechaSalida = Calendar.getInstance();
        return Calculadora.valorSubTotalParqueoVehiculo(fechaEntrada, fechaSalida, carro.getTipo());
    }
}
