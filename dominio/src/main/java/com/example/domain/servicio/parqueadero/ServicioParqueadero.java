package com.example.domain.servicio.parqueadero;

import androidx.lifecycle.MutableLiveData;

import com.example.domain.entidad.Carro;
import com.example.domain.entidad.Motocicleta;
import com.example.domain.entidad.Vehiculo;
import com.example.domain.excepcion.PlacaNoPermitidaExcepcion;
import com.example.domain.excepcion.SinCupoExcepcion;
import com.example.domain.repositorio.CarroRepositorio;
import com.example.domain.repositorio.MotocicletaRepositorio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class ServicioParqueadero {

    private final CarroRepositorio carroRepositorio;
    private final MotocicletaRepositorio motocicletaRepositorio;
    private final String LETRA_INICIAL_PLACA = "A";
    private final int DIA_LUNES = 1;
    private final int DIA_DOMINGO = 7;

    @Inject
    public ServicioParqueadero(CarroRepositorio carroRepositorio, MotocicletaRepositorio motocicletaRepositorio) {
        this.carroRepositorio = carroRepositorio;
        this.motocicletaRepositorio = motocicletaRepositorio;
    }

    public MutableLiveData<List<Vehiculo>> obtenerVehiculos() {
        MutableLiveData<List<Vehiculo>> listaMutableVehiculo = new MutableLiveData<>();
        List<Vehiculo> vehiculoLista = new ArrayList<>();
        vehiculoLista.addAll(carroRepositorio.obtenerCarros());
        vehiculoLista.addAll(motocicletaRepositorio.obtenerMotocicletas());
        listaMutableVehiculo.setValue(vehiculoLista);
        return listaMutableVehiculo;
    }

    public void guardarCarros(Carro carro) {
        byte cantidadCarros = carroRepositorio.obtenerCantidadCarros();
        int diaActual = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            diaActual = LocalDate.now().getDayOfWeek().getValue();
        }
        if (cantidadCarros == carro.CANTIDAD_MAXIMA_EN_PARQUEADERO) {
            throw new SinCupoExcepcion();
        } else if (validarPlaca(carro.obtenerPlaca(), diaActual)) {
            throw new PlacaNoPermitidaExcepcion();
        }
        carroRepositorio.guardarCarro(carro);

    }

    public void guardarMotocicletas(Motocicleta motocicleta) {
        byte cantidadMotociletas = motocicletaRepositorio.obtenerCantidadMotociletas();
        int diaActual = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            diaActual = LocalDate.now().getDayOfWeek().getValue();
        }
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
        return (placa.startsWith(LETRA_INICIAL_PLACA) && (diaActual == DIA_LUNES || diaActual == DIA_DOMINGO));
    }

    public int valorTotalParqueaderoMotocicleta(Motocicleta motocicleta) {
        return motocicleta.calcularValorTotalDeParqueadero(Calendar.getInstance(),motocicleta.VALOR_HORA_PARQUEADERO,motocicleta.VALOR_DIA_PARQUEADERO,motocicleta.obtenerCilindraje());
    }

    public int valorTotalParqueaderoCarro(Carro carro) {
        return carro.calcularValorTotalDeParqueadero(Calendar.getInstance(),carro.VALOR_HORA_PARQUEADERO,carro.VALOR_DIA_PARQUEADERO);
    }

}
