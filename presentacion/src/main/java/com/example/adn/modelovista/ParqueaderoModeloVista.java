package com.example.adn.modelovista;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.adn.R;
import com.example.domain.entidad.Carro;
import com.example.domain.entidad.Vehiculo;
import com.example.domain.servicio.parqueadero.ServicioParqueadero;

import java.util.List;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class ParqueaderoModeloVista extends ViewModel {

    public MutableLiveData<List<Vehiculo>> vehiculos;

    private ServicioParqueadero servicioParqueadero;

    private MutableLiveData<String> carroGuardado;

    private MutableLiveData<String> carroEliminado;

    private MutableLiveData<Integer> cantidadCarros;

    private Context contexto;

    @ViewModelInject
    public ParqueaderoModeloVista(ServicioParqueadero servicioParqueadero, @ApplicationContext Context contexto) {
        this.contexto = contexto;
        this.servicioParqueadero = servicioParqueadero;
        obtenerVehiculos();
    }

    private void obtenerVehiculos() {
        if (vehiculos == null)
            this.vehiculos = new MutableLiveData<>();
        vehiculos = servicioParqueadero.obtenerVehiculos();
    }

    public MutableLiveData<List<Vehiculo>> obtenerListaVehiculos() {
        return vehiculos;
    }


    public LiveData<String> guardarCarro(Carro carro) {
        if (carroGuardado == null)
            carroGuardado = new MutableLiveData<>();
        try {
            servicioParqueadero.guardarCarros(carro);
            carroGuardado.setValue(contexto.getString(R.string.guardado_exitoso));
        } catch (Exception excepcion) {
            carroGuardado.setValue(excepcion.getMessage());
        }
        return carroGuardado;
    }

    public LiveData<String> eliminarCarro(Carro carro) {
        if (carroEliminado == null)
            carroEliminado = new MutableLiveData<>();
        try {
            servicioParqueadero.eliminarCarro(carro);
            carroEliminado.setValue(contexto.getString(R.string.eliminado_exitoso));
        } catch (Exception excepcion) {
            carroEliminado.setValue(excepcion.getMessage());
        }
        return carroEliminado;
    }

    public LiveData<Integer> obtenerCantidadCarros() {
        if (cantidadCarros == null)
            cantidadCarros = new MutableLiveData<>();
        cantidadCarros.setValue(servicioParqueadero.obtenerCantidadCarros());
        return cantidadCarros;
    }

}
