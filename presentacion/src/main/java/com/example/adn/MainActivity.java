package com.example.adn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;
import com.example.adn.modelovista.ParqueaderoModeloVista;
import com.example.domain.entidad.Carro;
import com.example.domain.entidad.Vehiculo;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {


    private ParqueaderoModeloVista parqueaderoModeloVista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parqueaderoModeloVista = new ViewModelProvider(this).get(ParqueaderoModeloVista.class);

        for (Vehiculo vehiculo : parqueaderoModeloVista.vehiculos.getValue()) {
            System.out.println("***************** " + vehiculo.obtenerPlaca() + " *****************");
        }

        Carro carro = new Carro("TRI-LL9");
        parqueaderoModeloVista.guardarCarro(carro).observe(this, vehiculoGuardado -> {
            Toast.makeText(this,vehiculoGuardado+carro.obtenerPlaca(),Toast.LENGTH_SHORT).show();
        });

    }
}