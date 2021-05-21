package com.example.adn.moduloid;

import com.example.domain.repositorio.CarroRepositorio;
import com.example.domain.repositorio.MotocicletaRepositorio;
import com.example.infraestructura.repositorio.CarroRepositorioRoom;
import com.example.infraestructura.repositorio.MotocicletaRepositorioRoom;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn({ActivityComponent.class})
public abstract class VehiculoModulo {

    @Binds
    public abstract CarroRepositorio bindCarroRepositorio(CarroRepositorioRoom carroRepositorioRoom);

    @Binds
    public abstract MotocicletaRepositorio bindMotocicletaRepositorio(MotocicletaRepositorioRoom motocicletaRepositorioRoom);

}
