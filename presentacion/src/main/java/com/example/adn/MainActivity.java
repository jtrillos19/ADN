package com.example.adn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;
import com.example.adn.modelovista.ParqueaderoModeloVista;
import com.example.domain.entidad.Carro;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {


    private ParqueaderoModeloVista parqueaderoModeloVista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parqueaderoModeloVista = new ViewModelProvider(this).get(ParqueaderoModeloVista.class);
        Carro carro = new Carro("ASX-VF2");
        parqueaderoModeloVista.guardarCarro(carro).observe(this, vehiculoGuardado -> {
            if (vehiculoGuardado) {
                Toast.makeText(this, "Vehiculo guardado!" + carro.obtenerPlaca(), Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "El vehiculo no se ha podido guardar!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}