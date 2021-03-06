package com.example.adn.vehiculo.modulo;

import com.example.domain.carro.repositorio.CarroRepositorio;
import com.example.domain.motocicleta.repositorio.MotocicletaRepositorio;
import com.example.infraestructura.carro.repositorio.CarroRepositorioRoom;
import com.example.infraestructura.motocicleta.repositorio.MotocicletaRepositorioRoom;

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
