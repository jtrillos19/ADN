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

import com.example.domain.entidad.Carro;
import com.example.domain.entidad.Motocicleta;
import com.example.domain.entidad.Vehiculo;

public class DialogoVehiculo {

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
            Vehiculo vehiculo = crearVehiculo(tipoMoto, placa.getText().toString(), Integer.parseInt(cilindraje.getText().toString()));
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
        btnAgregar = vista.findViewById(R.id.btn_agregar);
        btnCancelar = vista.findViewById(R.id.btn_cancelar);
    }

    private static Vehiculo crearVehiculo(RadioButton rdMoto, String placa, int cilindraje) {
        Vehiculo vehiculo;
        if (rdMoto.isChecked()) {
            vehiculo = new Motocicleta(placa, cilindraje);
        } else {
            vehiculo = new Carro(placa);
        }
        return vehiculo;
    }

    private static void guardarVehiculo(Activity actividad, Vehiculo vehiculo, AlertDialog dialogo) {
        if (actividad instanceof MainActivity) {
            ((MainActivity) actividad).guardarVehiculo(vehiculo,dialogo);
        }
    }
}
