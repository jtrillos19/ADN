package com.example.infraestructura.repositorio;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.domain.entidad.Motocicleta;
import com.example.domain.repositorio.MotocicletaRepositorio;
import com.example.infraestructura.anticorrupcion.MotocicletaTraductor;
import com.example.infraestructura.db.BaseDatosAdministrador;
import com.example.infraestructura.db.dao.MotocicletaDao;
import com.example.infraestructura.db.entidad.MotocicletaEntidad;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class MotocicletaRepositorioRoom implements MotocicletaRepositorio {

    private BaseDatosAdministrador baseDatosAdministrador;
    private MotocicletaDao motocicletaDao;

    @Inject
    public MotocicletaRepositorioRoom(@ApplicationContext Context contexto) {
        baseDatosAdministrador = BaseDatosAdministrador.obtenerInstancia(contexto);
        motocicletaDao = baseDatosAdministrador.motocicletaDao();
    }

    @Override
    public List<Motocicleta> obtenerMotocicletas() {
        List<Motocicleta> motocicletasDominio = new ArrayList<>();
        ObtenerListaMotocicletasAsincrono obtenerListaMotocicletasAsincrono = new ObtenerListaMotocicletasAsincrono();
        try {
            List<MotocicletaEntidad> motocicletaDB = obtenerListaMotocicletasAsincrono.execute().get();
            MotocicletaTraductor.pasarListaMotocicletaBDAListaMotocicletaDominio(motocicletaDB);
        } catch (Exception e) {
            Log.e("Listar Motocicletas DB", e.getMessage());
        }
        return motocicletasDominio;
    }

    @Override
    public void guardarMotocicleta(Motocicleta motocicleta) {
        MotocicletaEntidad motocicletaEntidad = MotocicletaTraductor.pasarMotocicletaDominioAMotocicletaBD(motocicleta);
       BaseDatosAdministrador.EJECUTOR_ESCRITURA_BD.execute(()->{
           motocicletaDao.agregarMotocicleta(motocicletaEntidad);
       });
    }

    @Override
    public void eliminarMotocicleta(Motocicleta motocicleta) {
        MotocicletaEntidad motocicletaEntidad = MotocicletaTraductor.pasarMotocicletaDominioAMotocicletaBD(motocicleta);
       BaseDatosAdministrador.EJECUTOR_ESCRITURA_BD.execute(()->{
           motocicletaDao.eliminarMotocicleta(motocicletaEntidad);
       });
    }

    @Override
    public byte obtenerCantidadMotociletas() {
        byte cantidadMotocicletas = 0;
        ObtenerCantidadMotocicletasAsincrono obtenerCantidadMotocicletasAsincrono = new ObtenerCantidadMotocicletasAsincrono();
        try {
            cantidadMotocicletas = obtenerCantidadMotocicletasAsincrono.execute().get();
        } catch (Exception excepcion) {
            Log.println(Log.ERROR, MotocicletaRepositorioRoom.class.getName(), excepcion.getMessage());
        }
        return cantidadMotocicletas;
    }

    class ObtenerListaMotocicletasAsincrono extends AsyncTask<Void, Void, List<MotocicletaEntidad>> {
        @Override
        protected List<MotocicletaEntidad> doInBackground(Void... voids) {
            return motocicletaDao.obtenerMotocicletas();
        }
    }

    class ObtenerCantidadMotocicletasAsincrono extends AsyncTask<Void, Void, Byte> {
        @Override
        protected Byte doInBackground(Void... voids) {
            return motocicletaDao.obtenerCantidadMotocicletas();
        }
    }
}
