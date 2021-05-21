package com.example.adn;

import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class ActividadPrincipalPruebas {

    @Rule
    public ActivityTestRule<ActividadPrincipal> mainActivityActivityTestRule = new ActivityTestRule<>(ActividadPrincipal.class);

    @Test
    public void guardarCarro_carroAgregadoEnVistaReciclada_verCarroEnPantalla() {
        //Arrange
        String placa = "AXCTR4";
        //Act
        ActividadPrincipalPagina.clicBoton(ActividadPrincipalPagina.obtenerRecursoBotonIngresarVehiculo());
        DialogoVehiculoPagina.clicBoton(DialogoVehiculoPagina.obtenerRecursoTipoCarro());
        DialogoVehiculoPagina.editarCajaTexto(DialogoVehiculoPagina.obtenerRecursoCajaTextoPlaca(), placa);
        //Assert
        DialogoVehiculoPagina.clicBoton(DialogoVehiculoPagina.obtenerRecursoButonAgregarVehiculo());
    }

    @Test
    public void guardarMoto_motoAgregadoEnVistaReciclada_verMotoEnPantalla() {
        //Arrange
        String placa = "QWCTR4";
        String cilindraje = "200";
        //Act
        //Act
        ActividadPrincipalPagina.clicBoton(ActividadPrincipalPagina.obtenerRecursoBotonIngresarVehiculo());
        DialogoVehiculoPagina.clicBoton(DialogoVehiculoPagina.obtenerRecursoTipoMoto());
        DialogoVehiculoPagina.editarCajaTexto(DialogoVehiculoPagina.obtenerRecursoCajaTextoPlaca(), placa);
        DialogoVehiculoPagina.editarCajaTexto(DialogoVehiculoPagina.obtenerRecursoCajaTextoCilindraje(), cilindraje);
        //Assert
        DialogoVehiculoPagina.clicBoton(DialogoVehiculoPagina.obtenerRecursoButonAgregarVehiculo());
    }

}
