package com.example.adn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.domain.entidad.Carro;
import com.example.domain.servicio.parqueadero.ServicioParqueadero;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    ServicioParqueadero servicioParqueadero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Carro carro = new Carro("ASX-VF2");
        servicioParqueadero.guardarCarros(carro);
    }
}