package com.example.adn.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adn.R;
import com.example.domain.entidad.Motocicleta;
import com.example.domain.entidad.Vehiculo;

import java.util.List;

public class VehiculoAdaptador extends RecyclerView.Adapter<VehiculoAdaptador.VehiculoViewHolder> {

    private List<Vehiculo> vehiculos;

    public VehiculoAdaptador(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    @NonNull
    @Override
    public VehiculoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VehiculoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_vehiculos, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VehiculoViewHolder holder, int position) {
        Vehiculo vehiculo = vehiculos.get(position);
        holder.iniciarElementos(vehiculo);
    }

    @Override
    public int getItemCount() {
        return vehiculos.size();
    }

    public class VehiculoViewHolder extends RecyclerView.ViewHolder {

        private TextView placa;
        private TextView fechaIngreso;
        private TextView cilindraje;
        private LinearLayout contenedorCilindraje;

        public VehiculoViewHolder(@NonNull View vista) {
            super(vista);
            placa = vista.findViewById(R.id.placa);
            fechaIngreso = vista.findViewById(R.id.fechaIngreso);
            contenedorCilindraje = vista.findViewById(R.id.contenedorCilindraje);
            cilindraje = vista.findViewById(R.id.cilingraje);
        }

        public void iniciarElementos(Vehiculo vehiculo) {
            this.placa.setText(vehiculo.obtenerPlaca());
            this.fechaIngreso.setText(vehiculo.obtenerFechaIngreso().getTime().toString());
            if (vehiculo instanceof Motocicleta) {
                Motocicleta motocicleta = (Motocicleta) vehiculo;
                contenedorCilindraje.setVisibility(View.VISIBLE);
                this.cilindraje.setText(String.valueOf(motocicleta.obtenerCilindraje()));
            }
        }
    }
}
