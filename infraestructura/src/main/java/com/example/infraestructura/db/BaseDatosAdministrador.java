package com.example.infraestructura.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.infraestructura.db.dao.CarroDao;
import com.example.infraestructura.db.dao.MotocicletaDao;
import com.example.infraestructura.db.entidad.CarroEntidad;
import com.example.infraestructura.db.entidad.MotocicletaEntidad;

@Database(entities = {CarroEntidad.class, MotocicletaEntidad.class}, version = 1)
public abstract class BaseDatosAdministrador extends RoomDatabase {

    private static BaseDatosAdministrador instancia = null;

    public abstract CarroDao carroDao();

    public abstract MotocicletaDao motocicletaDao();

    public static BaseDatosAdministrador obtenerInstancia(Context context) {
        if (instancia == null) {
            instancia = Room.databaseBuilder(context,
                    BaseDatosAdministrador.class, "adn-ceiba").build();
        }
        return instancia;
    }
}
