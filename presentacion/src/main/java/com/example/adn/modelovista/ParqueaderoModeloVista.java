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

    private MutableLiveData<String> vehiculoGuardado;

    private Context contexto;

    @ViewModelInject
    public ParqueaderoModeloVista(ServicioParqueadero servicioParqueadero, @ApplicationContext Context contexto) {
        this.contexto = contexto;
        this.servicioParqueadero = servicioParqueadero;
        iniciarVariables();
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

    public void iniciarVariables() {
        if (vehiculoGuardado == null) {
            vehiculoGuardado = new MutableLiveData<>();
        }
    }

    public LiveData<String> guardarCarro(Carro carro) {
        try {
            servicioParqueadero.guardarCarros(carro);
            vehiculoGuardado.setValue(contexto.getString(R.string.guardado_exitoso));
        } catch (Exception exception) {
            vehiculoGuardado.setValue(exception.getMessage());
        }
        return vehiculoGuardado;
    }

}
