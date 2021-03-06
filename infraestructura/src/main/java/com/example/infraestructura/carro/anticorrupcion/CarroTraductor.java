package com.example.infraestructura.carro.anticorrupcion;

import com.example.domain.carro.entidad.Carro;
import com.example.infraestructura.carro.db.entidad.CarroEntidad;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CarroTraductor {

    public static Carro pasarCarroDBaCarroDominio(CarroEntidad carroEntidad) throws ParseException {
        Carro carro = new Carro(carroEntidad.placa);
        Calendar fechaEntrada = Calendar.getInstance();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("EEEE MMMM d HH:mm:ss z yyyy");
        fechaEntrada.setTime(formatoFecha.parse(String.valueOf(carroEntidad.fechaEntrada)));
        carro.modificarFechaIngreso(fechaEntrada);
        return carro;
    }

    public static CarroEntidad pasarCarroDominioACarroDB(Carro carro) {
        CarroEntidad carroEntidad = new CarroEntidad();
        carroEntidad.modificarPlaca(carro.obtenerPlaca());
        carroEntidad.modificarFechaEntrada(carro.obtenerFechaIngreso().getTime().toString());
        return carroEntidad;
    }

    public static List<Carro> pasarListaCarroDBaListaCarroDominio(List<CarroEntidad> listaCarros) throws ParseException{
        List<Carro> listaCarrosTemporal = new ArrayList<>();
        for (CarroEntidad carroEntidad : listaCarros){
            listaCarrosTemporal.add(pasarCarroDBaCarroDominio(carroEntidad));
        }
        return listaCarrosTemporal;
    }

}
