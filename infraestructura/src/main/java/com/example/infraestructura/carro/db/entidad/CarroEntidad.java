package com.example.infraestructura.carro.db.entidad;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "carro_entidad")
public class CarroEntidad {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "placa")
    public String placa;

    @ColumnInfo(name = "fechaEntrada")
    public String fechaEntrada;

    public void modificarPlaca(String placa) {
        this.placa = placa;
    }

    public void modificarFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
}
