package com.example.infraestructura.anticorrupcion;

import com.example.domain.entidad.Motocicleta;
import com.example.infraestructura.db.entidad.MotocicletaEntidad;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MotocicletaTraductor {

    public static Motocicleta pasarMotoBDaMotoDominio(MotocicletaEntidad motocicletaEntidad) throws ParseException {
        Motocicleta motocicleta = new Motocicleta(motocicletaEntidad.placa, motocicletaEntidad.cilindraje);
        Calendar fechaEntrada = Calendar.getInstance();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("EEEE MMMM d HH:mm:ss z yyyy");
        fechaEntrada.setTime(formatoFecha.parse(String.valueOf(motocicletaEntidad.fechaEntrada)));
        motocicleta.modificarFechaIngreso(fechaEntrada);
        return motocicleta;
    }

    public static MotocicletaEntidad pasarMotocicletaDominioAMotocicletaBD(Motocicleta motocicleta) {
        MotocicletaEntidad motocicletaEntidad = new MotocicletaEntidad();
        motocicletaEntidad.modificarPlaca(motocicleta.obtenerPlaca());
        motocicletaEntidad.modificarCilindraje(motocicleta.obtenerCilindraje());
        motocicletaEntidad.modificarFechaEntrada(motocicleta.obtenerFechaIngreso().getTime().toString());
        return motocicletaEntidad;
    }

    public static List<Motocicleta> pasarListaMotocicletaBDAListaMotocicletaDominio(List<MotocicletaEntidad> listaMotos) throws ParseException {
        List<Motocicleta> listaMotocicletasTemporal = new ArrayList<>();
        for (MotocicletaEntidad motocicletaEntidad : listaMotos) {
            listaMotocicletasTemporal.add(pasarMotoBDaMotoDominio(motocicletaEntidad));
        }
        return listaMotocicletasTemporal;
    }
}
