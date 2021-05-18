package com.example.infraestructura.repositorio;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.domain.entidad.Carro;
import com.example.domain.repositorio.CarroRepositorio;
import com.example.infraestructura.anticorrupcion.CarroTraductor;
import com.example.infraestructura.db.BaseDatosAdministrador;
import com.example.infraestructura.db.dao.CarroDao;
import com.example.infraestructura.db.entidad.CarroEntidad;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class CarroRepositorioRoom implements CarroRepositorio {

    private BaseDatosAdministrador baseDatosAdministrador;
    private CarroDao carroDao;

    @Inject
    public CarroRepositorioRoom(@ApplicationContext Context context) {
        baseDatosAdministrador = BaseDatosAdministrador.obtenerInstancia(context);
        carroDao = baseDatosAdministrador.carroDao();
    }

    @Override
    public List<Carro> obtenerCarros() {
        List<Carro> carrosDominio = new ArrayList<>();
        ObtenerCarrosAsyncTask obtenerCarrosAsyncTask = new ObtenerCarrosAsyncTask();
        try {
            List<CarroEntidad> carrosDB = obtenerCarrosAsyncTask.execute().get();
            CarroTraductor.pasarListaCarroDominioAListaCarroDB(carrosDB);
        } catch (Exception e) {
            Log.e("Consulta DB Carro", e.getMessage());
        }
        return carrosDominio;
    }

    @Override
    public void guardarCarro(Carro carro) {
        AgregarCarrosAsyncTask agregarCarrosAsyncTask = new AgregarCarrosAsyncTask();
        CarroEntidad carroEntidad = CarroTraductor.pasarCarroDominioACarroDB(carro);
        agregarCarrosAsyncTask.execute(carroEntidad);
    }

    @Override
    public void eliminarCarro(Carro carro) {

    }

    @Override
    public byte obtenerCantidadCarros() {
        return 0;
    }

    class ObtenerCarrosAsyncTask extends AsyncTask<Void, Void, List<CarroEntidad>> {
        @Override
        protected List<CarroEntidad> doInBackground(Void... voids) {
            return carroDao.obtenerCarros();
        }
    }

    class AgregarCarrosAsyncTask extends AsyncTask<CarroEntidad, Void, Void> {
        @Override
        protected Void doInBackground(CarroEntidad... carroEntidades) {
            carroDao.agregarCarro(carroEntidades[0]);
            return null;
        }
    }
}
