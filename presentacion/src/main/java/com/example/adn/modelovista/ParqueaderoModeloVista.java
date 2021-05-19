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

    private MutableLiveData<String> carroGuardado;

    private MutableLiveData<Integer> valorPagarCarros;

    private MutableLiveData<String> motocicletaGuardado;

    private MutableLiveData<Integer> valorPagarMotocicletas;

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

    public LiveData<Integer> calcularValorTotalPagarCarro(Carro carro) {
        if (valorPagarCarros == null)
            valorPagarCarros = new MutableLiveData<>();
        valorPagarCarros.setValue(servicioParqueadero.valorTotalParqueaderoCarro(carro));
        servicioParqueadero.eliminarCarro(carro);
        return valorPagarCarros;
    }
    public LiveData<String> guardarMotocicleta(Motocicleta motocicleta) {
        if (motocicletaGuardado == null)
            motocicletaGuardado = new MutableLiveData<>();
        try {
            servicioParqueadero.guardarMotocicletas(motocicleta);
            motocicletaGuardado.setValue(contexto.getString(R.string.guardado_exitoso));
        } catch (Exception excepcion) {
            motocicletaGuardado.setValue(excepcion.getMessage());
        }
        return motocicletaGuardado;
    }

    public LiveData<Integer> calcularValorTotalPagarMoto(Motocicleta motocicleta) {
        if (valorPagarMotocicletas == null)
            valorPagarMotocicletas = new MutableLiveData<>();
        valorPagarMotocicletas.setValue(servicioParqueadero.valorTotalParqueaderoMotocicleta(motocicleta));
        servicioParqueadero.eliminarMotocicleta(motocicleta);
        return valorPagarMotocicletas;
    }

}
