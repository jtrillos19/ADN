package com.example.adn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.adn.adaptador.VehiculoAdaptador;
import com.example.adn.modelovista.ParqueaderoModeloVista;
import com.example.domain.entidad.Carro;
import com.example.domain.entidad.Vehiculo;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {


    private ParqueaderoModeloVista parqueaderoModeloVista;
    private RecyclerView vistaReciclada;
    private VehiculoAdaptador vehiculoAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parqueaderoModeloVista = new ViewModelProvider(this).get(ParqueaderoModeloVista.class);
        vistaReciclada = findViewById(R.id.vistaRecicladaVehiculos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        vistaReciclada.setLayoutManager(linearLayoutManager);
        parqueaderoModeloVista.obtenerListaVehiculos().observe(this, vehiculos -> {
            vehiculoAdaptador = new VehiculoAdaptador(vehiculos);
            vistaReciclada.setAdapter(vehiculoAdaptador);
        });


    }
}