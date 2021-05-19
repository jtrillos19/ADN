package com.example.adn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.adn.adaptador.VehiculoAdaptador;
import com.example.adn.modelovista.ParqueaderoModeloVista;
import com.example.domain.entidad.Carro;
import com.example.domain.entidad.Motocicleta;
import com.example.domain.entidad.Vehiculo;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {


    private ParqueaderoModeloVista parqueaderoModeloVista;
    private RecyclerView vistaReciclada;
    private VehiculoAdaptador vehiculoAdaptador;
    private Button btnIngresarVehiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarElementos();
        btnIngresarVehiculo.setOnClickListener(v -> {
            if (true){
                Carro carro = new Carro("TRI-19M");
                parqueaderoModeloVista.guardarCarro(carro).observe(this,vehiculo->{
                    vehiculoAdaptador.notifyDataSetChanged();
                });
            }
        });
        parqueaderoModeloVista.obtenerListaVehiculos().observe(this,this::actualizarAdaptador);
    }
    private void iniciarElementos() {
        vistaReciclada = findViewById(R.id.vistaRecicladaVehiculos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        vistaReciclada.setLayoutManager(linearLayoutManager);
        btnIngresarVehiculo = findViewById(R.id.btnIngresarVehiculo);
        parqueaderoModeloVista = new ViewModelProvider(this).get(ParqueaderoModeloVista.class);
    }

    private void actualizarAdaptador(List<Vehiculo> vehiculos) {
        vehiculoAdaptador = new VehiculoAdaptador(vehiculos, this);
        vistaReciclada.setAdapter(vehiculoAdaptador);
    }

    public void cobrarParqueadero(Vehiculo vehiculo) {
        AtomicInteger valorTotalPagar = new AtomicInteger();
        if (vehiculo instanceof Carro) {
            Carro carro = (Carro) vehiculo;
            parqueaderoModeloVista.calcularValorTotalPagarCarro(carro).observe(this, valorPagar -> {
                valorTotalPagar.set(valorPagar);
            });
        } else {
            Motocicleta motocicleta = (Motocicleta) vehiculo;
            parqueaderoModeloVista.calcularValorTotalPagarMoto(motocicleta).observe(this, valorPagar -> {
                valorTotalPagar.set(valorPagar);
            });
        }
        Toast.makeText(this, "Total a Pagar: " + valorTotalPagar.get(), Toast.LENGTH_SHORT).show();
        vehiculoAdaptador.notifyDataSetChanged();
    }
}