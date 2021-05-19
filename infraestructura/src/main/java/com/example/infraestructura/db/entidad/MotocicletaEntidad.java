package com.example.infraestructura.db.entidad;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "motocicleta_entidad")
public class MotocicletaEntidad {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "placa")
    public String placa;

    @ColumnInfo(name = "fechaEntrada")
    public String fechaEntrada;

    @ColumnInfo(name = "cilindraje")
    public int cilindraje;

    public void modificarPlaca(String placa) {
        this.placa = placa;
    }

    public void modificarFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public void modificarCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }
}
