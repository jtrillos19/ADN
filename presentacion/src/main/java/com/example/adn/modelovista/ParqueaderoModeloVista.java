package com.example.adn.modelovista;

import android.os.AsyncTask;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.domain.entidad.Carro;
import com.example.domain.entidad.Vehiculo;
import com.example.domain.servicio.parqueadero.ServicioParqueadero;

import java.util.List;

public class ParqueaderoModeloVista extends ViewModel {

    private MutableLiveData<List<Vehiculo>> vehiculos;

    private ServicioParqueadero servicioParqueadero;

    private MutableLiveData<Boolean> vehiculoGuardado;

    @ViewModelInject
    public ParqueaderoModeloVista(ServicioParqueadero servicioParqueadero) {
        this.servicioParqueadero = servicioParqueadero;
        this.vehiculos = new MutableLiveData<>();
        iniciarVariables();
    }

    public MutableLiveData<List<Vehiculo>> obtenerListaVehiculos() {
        vehiculos.setValue(servicioParqueadero.obtenerVehiculos());
        return vehiculos;
    }

    public void iniciarVariables() {
        if (vehiculoGuardado == null) {
            vehiculoGuardado = new MutableLiveData<>();
        }
    }

    public LiveData<Boolean> guardarCarro(Carro carro) {
        GuardarCarroAsincrono guardarCarroAsincrono = new GuardarCarroAsincrono();
        guardarCarroAsincrono.execute(carro);
        return vehiculoGuardado;
    }

    class GuardarCarroAsincrono extends AsyncTask<Carro, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Carro... carro) {
            try {
                servicioParqueadero.guardarCarros(carro[0]);
                vehiculos.getValue().add(carro[0]);
                return true;
            } catch (Exception excepcion) {
                Log.println(Log.ERROR, ParqueaderoModeloVista.class.getName(), excepcion.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean guardado) {
            vehiculoGuardado.setValue(guardado);
        }
    }
}
