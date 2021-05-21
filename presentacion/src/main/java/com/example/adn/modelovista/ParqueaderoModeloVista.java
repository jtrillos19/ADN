package com.example.adn.modelovista;

import android.content.Context;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.adn.R;
import com.example.domain.carro.entidad.Carro;
import com.example.domain.motocicleta.entidad.Motocicleta;
import com.example.domain.vehiculo.entidad.Vehiculo;
import com.example.domain.parqueadero.servicio.ParqueaderoServicio;

import java.util.List;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class ParqueaderoModeloVista extends ViewModel {

    public MutableLiveData<List<Vehiculo>> vehiculos;

    private ParqueaderoServicio parqueaderoServicio;

    private MutableLiveData<Integer> totalPagar;

    private MutableLiveData<String> vehiculoGuardado;

    private Context contexto;



    @ViewModelInject
    public ParqueaderoModeloVista(ParqueaderoServicio parqueaderoServicio, @ApplicationContext Context contexto) {
        this.contexto = contexto;
        this.parqueaderoServicio = parqueaderoServicio;
        obtenerVehiculos();
    }

    private void obtenerVehiculos() {
        if (vehiculos == null)
            this.vehiculos = new MutableLiveData<>();
        vehiculos = parqueaderoServicio.obtenerVehiculos();
    }

    public MutableLiveData<List<Vehiculo>> obtenerListaVehiculos() {
        return vehiculos;
    }

    public LiveData<Integer> calcularValorTotalPagarVehiculo(Vehiculo vehiculo) {
        if (totalPagar == null)
            totalPagar = new MutableLiveData<>();
        if (vehiculo instanceof Carro) {
            Carro carro = (Carro) vehiculo;
            totalPagar.setValue(parqueaderoServicio.valorTotalParqueaderoCarro(carro));
            parqueaderoServicio.eliminarCarro(carro);
        } else {
            Motocicleta motocicleta = (Motocicleta) vehiculo;
            totalPagar.setValue(parqueaderoServicio.valorTotalParqueaderoMotocicleta(motocicleta));
            parqueaderoServicio.eliminarMotocicleta(motocicleta);
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
                parqueaderoServicio.guardarCarros(carro);
            } else {
                Motocicleta motocicleta = (Motocicleta) vehiculo;
                parqueaderoServicio.guardarMotocicletas(motocicleta);
            }
            vehiculos.getValue().add(vehiculo);
            vehiculoGuardado.setValue(contexto.getString(R.string.guardado_exitoso));
        } catch (Exception excepcion) {
            vehiculoGuardado.setValue(excepcion.getMessage());
        }
        return vehiculoGuardado;
    }


}
