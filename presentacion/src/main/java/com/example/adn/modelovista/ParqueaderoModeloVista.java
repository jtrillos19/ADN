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
import com.example.domain.entidad.Motocicleta;
import com.example.domain.entidad.Vehiculo;
import com.example.domain.servicio.parqueadero.ServicioParqueadero;

import java.util.List;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class ParqueaderoModeloVista extends ViewModel {

    public MutableLiveData<List<Vehiculo>> vehiculos;

    private ServicioParqueadero servicioParqueadero;

    private MutableLiveData<Integer> totalPagar;

    private MutableLiveData<String> vehiculoGuardado;

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

    public LiveData<Integer> calcularValorTotalPagarVehiculo(Vehiculo vehiculo) {
        if (totalPagar == null)
            totalPagar = new MutableLiveData<>();
        if (vehiculo instanceof Carro) {
            Carro carro = (Carro) vehiculo;
            totalPagar.setValue(servicioParqueadero.valorTotalParqueaderoCarro(carro));
            servicioParqueadero.eliminarCarro(carro);
        } else {
            Motocicleta motocicleta = (Motocicleta) vehiculo;
            totalPagar.setValue(servicioParqueadero.valorTotalParqueaderoMotocicleta(motocicleta));
            servicioParqueadero.eliminarMotocicleta(motocicleta);
        }
        vehiculos.getValue().remove(vehiculo);
        return totalPagar;
    }

    public LiveData<String> guardarVehiculo(Vehiculo vehiculo) {
        if (vehiculoGuardado == null)
            vehiculoGuardado = new MutableLiveData<>();
        try {
            if (vehiculo instanceof Carro) {
                Carro carro = (Carro) vehiculo;
                servicioParqueadero.guardarCarros(carro);
            } else {
                Motocicleta motocicleta = (Motocicleta) vehiculo;
                servicioParqueadero.guardarMotocicletas(motocicleta);
            }
            vehiculos.getValue().add(vehiculo);
            vehiculoGuardado.setValue(contexto.getString(R.string.guardado_exitoso));
        } catch (Exception excepcion) {
            vehiculoGuardado.setValue(excepcion.getMessage());
        }
        return vehiculoGuardado;
    }


}
