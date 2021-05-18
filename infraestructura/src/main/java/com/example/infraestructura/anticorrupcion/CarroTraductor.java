package com.example.infraestructura.anticorrupcion;

import com.example.domain.entidad.Carro;
import com.example.infraestructura.db.entidad.CarroEntidad;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CarroTraductor {

    public static Carro pasarCarroDBaCarroDominio(CarroEntidad carroEntidad) throws ParseException {
        Carro carro = new Carro(carroEntidad.placa, carroEntidad.tipo);
        Calendar fechaIngreso = Calendar.getInstance();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        fechaIngreso.setTime(formatoFecha.parse(String.valueOf(fechaIngreso)));
        carro.modificarFechaIngreso(fechaIngreso);
        return carro;
    }

    public static CarroEntidad pasarCarroDominioACarroDB(Carro carro) {
        CarroEntidad carroEntidad = new CarroEntidad();
        carroEntidad.modificarPlaca(carro.obtenerPlaca());
        carroEntidad.modificarTipo(carro.obtenerTipo());
        carroEntidad.modificarFechaEntrada(carro.obtenerFechaIngreso().toString());
        return carroEntidad;
    }

    public static List<Carro> pasarListaCarroDominioAListaCarroDB(List<CarroEntidad> listaCarros) throws ParseException{
        List<Carro> listaCarrosTemporal = new ArrayList<>();
        for (CarroEntidad carroEntidad : listaCarros){
            listaCarrosTemporal.add(pasarCarroDBaCarroDominio(carroEntidad));
        }
        return listaCarrosTemporal;
    }

}