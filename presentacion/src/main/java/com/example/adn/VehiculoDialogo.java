package com.example.adn;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.appcompat.app.AlertDialog;

import com.example.domain.carro.entidad.Carro;
import com.example.domain.motocicleta.entidad.Motocicleta;
import com.example.domain.vehiculo.entidad.Vehiculo;

public class VehiculoDialogo {

    private static LinearLayout contenedorCilindraje;
    private static RadioButton tipoCarro;
    private static RadioButton tipoMoto;
    private static EditText placa;
    private static EditText cilindraje;
    private static Button btnAgregar;
    private static Button btnCancelar;
    private static AlertDialog.Builder dialogo;

    public static AlertDialog crearDialogoGuardarVehiculo(Activity activity) {
        dialogo = new AlertDialog.Builder(activity);
        LayoutInflater disenio = activity.getLayoutInflater();
        View vista = disenio.inflate(R.layout.dialogo_agregar_vehiculo, null);
        iniciarElementos(vista);
        AlertDialog dialogoTmp = dialogo.create();
        dialogoTmp.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        tipoMoto.setOnClickListener(v -> contenedorCilindraje.setVisibility(View.VISIBLE));
        tipoCarro.setOnClickListener(v -> {
            if (contenedorCilindraje.getVisibility() == View.VISIBLE)
                contenedorCilindraje.setVisibility(View.GONE);
        });
        btnCancelar.setOnClickListener(v -> dialogoTmp.dismiss());
        btnAgregar.setOnClickListener(v -> {
            Vehiculo vehiculo = crearVehiculo(tipoMoto, placa.getText().toString(), cilindraje.getText().toString());
            guardarVehiculo(activity, vehiculo, dialogoTmp);
        });
        return dialogoTmp;
    }

    private static void iniciarElementos(View vista) {
        dialogo.setView(vista);
        contenedorCilindraje = vista.findViewById(R.id.contenedorCilindraje);
        tipoCarro = vista.findViewById(R.id.tipoCarro);
        tipoMoto = vista.findViewById(R.id.tipoMoto);
        placa = vista.findViewById(R.id.placa);
        cilindraje = vista.findViewById(R.id.cilindraje);
        btnAgregar = vista.findViewById(R.id.btnAgregar);
        btnCancelar = vista.findViewById(R.id.btnCancelar);
    }

    private static Vehiculo crearVehiculo(RadioButton rdMoto, String placa, String cilindraje) {
        Vehiculo vehiculo;
        if (rdMoto.isChecked()) {
            vehiculo = new Motocicleta(placa, Integer.parseInt(cilindraje));
        } else {
            vehiculo = new Carro(placa);
        }
        return vehiculo;
    }

    private static void guardarVehiculo(Activity actividad, Vehiculo vehiculo, AlertDialog dialogo) {
        if (actividad instanceof ActividadPrincipal) {
            ((ActividadPrincipal) actividad).guardarVehiculo(vehiculo,dialogo);
        }
    }
}
