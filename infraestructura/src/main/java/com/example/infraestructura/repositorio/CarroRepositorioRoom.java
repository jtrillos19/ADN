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
    public CarroRepositorioRoom(@ApplicationContext Context contexto) {
        baseDatosAdministrador = BaseDatosAdministrador.obtenerInstancia(contexto);
        carroDao = baseDatosAdministrador.carroDao();
    }

    @Override
    public List<Carro> obtenerCarros() {
        List<Carro> carrosDominio = new ArrayList<>();
        ObtenerListaCarrosAsincrono obtenerListaCarrosAsincrono = new ObtenerListaCarrosAsincrono();
        try {
            List<CarroEntidad> carrosDB = obtenerListaCarrosAsincrono.execute().get();
            carrosDominio.addAll(CarroTraductor.pasarListaCarroDBaListaCarroDominio(carrosDB));
        } catch (Exception exception) {
            Log.println(Log.ERROR, CarroRepositorioRoom.class.getName(), exception.getMessage());
        }
        return carrosDominio;
    }

    @Override
    public void guardarCarro(Carro carro) {
        CarroEntidad carroEntidad = CarroTraductor.pasarCarroDominioACarroDB(carro);
        BaseDatosAdministrador.EJECUTOR_ESCRITURA_BD.execute(() -> {
            carroDao.agregarCarro(carroEntidad);
        });
    }

    @Override
    public void eliminarCarro(Carro carro) {
        EliminarCarroAsincrono eliminarCarroAsincrono = new EliminarCarroAsincrono();
        CarroEntidad carroEntidad = CarroTraductor.pasarCarroDominioACarroDB(carro);
        eliminarCarroAsincrono.execute(carroEntidad);
    }

    @Override
    public byte obtenerCantidadCarros() {
        byte cantidadCarros = 0;
        ObtenerCantidadCarrosAsincrono obtenerCantidadCarrosAsincrono = new ObtenerCantidadCarrosAsincrono();
        try {
            cantidadCarros = obtenerCantidadCarrosAsincrono.execute().get();
        } catch (Exception e) {
            Log.e("BD total carros", e.getMessage());
        }
        return cantidadCarros;
    }

    class ObtenerListaCarrosAsincrono extends AsyncTask<Void, Void, List<CarroEntidad>> {
        @Override
        protected List<CarroEntidad> doInBackground(Void... voids) {
            return carroDao.obtenerCarros();
        }
    }

    class EliminarCarroAsincrono extends AsyncTask<CarroEntidad, Void, Void> {

        @Override
        protected Void doInBackground(CarroEntidad... carroEntidades) {
            carroDao.eliminarCarro(carroEntidades[0]);
            return null;
        }
    }

    class ObtenerCantidadCarrosAsincrono extends AsyncTask<Void, Void, Byte> {

        @Override
        protected Byte doInBackground(Void... voids) {
            return carroDao.obtenerCantidadCarros();
        }
    }
}
