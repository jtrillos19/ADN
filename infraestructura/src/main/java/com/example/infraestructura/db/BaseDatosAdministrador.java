package com.example.infraestructura.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.infraestructura.db.dao.CarroDao;
import com.example.infraestructura.db.dao.MotocicletaDao;
import com.example.infraestructura.db.entidad.CarroEntidad;
import com.example.infraestructura.db.entidad.MotocicletaEntidad;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CarroEntidad.class, MotocicletaEntidad.class}, version = 1)
public abstract class BaseDatosAdministrador extends RoomDatabase {

    private static BaseDatosAdministrador instancia = null;

    private static final int NUMERO_DE_HILOS = 4;
    public static final ExecutorService EJECUTOR_ESCRITURA_BD = Executors.newFixedThreadPool(NUMERO_DE_HILOS);

    public abstract CarroDao carroDao();

    public abstract MotocicletaDao motocicletaDao();

    public static BaseDatosAdministrador obtenerInstancia(Context contexto) {
        if (instancia == null) {
            synchronized (BaseDatosAdministrador.class) {
                if (instancia == null) {
                    instancia = Room.databaseBuilder(contexto,
                            BaseDatosAdministrador.class, "adn-ceiba").build();
                }
            }
        }
        return instancia;
    }
}
