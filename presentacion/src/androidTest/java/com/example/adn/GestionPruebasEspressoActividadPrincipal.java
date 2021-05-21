package com.example.adn;

import androidx.test.espresso.action.ViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class GestionPruebasEspressoActividadPrincipal {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void guardarCarro_carroAgregadoEnVistaReciclada_verCarroEnPantalla() {
        //Arrange
        String placa = "AXCTR4";
        //Act
        PaginaActividadPrincipal.clicBoton(PaginaActividadPrincipal.obtenerRecursoBotonIngresarVehiculo());
        PaginaDialogoVehiculo.clicBoton(PaginaDialogoVehiculo.obtenerRecursoTipoCarro());
        PaginaDialogoVehiculo.editarCajaTexto(PaginaDialogoVehiculo.obtenerRecursoCajaTextoPlaca(), placa);
        //Assert
        PaginaDialogoVehiculo.clicBoton(PaginaDialogoVehiculo.obtenerRecursoButonAgregarVehiculo());
    }

    @Test
    public void guardarMoto_motoAgregadoEnVistaReciclada_verMotoEnPantalla() {
        //Arrange
        String placa = "QWCTR4";
        String cilindraje = "200";
        //Act
        //Act
        PaginaActividadPrincipal.clicBoton(PaginaActividadPrincipal.obtenerRecursoBotonIngresarVehiculo());
        PaginaDialogoVehiculo.clicBoton(PaginaDialogoVehiculo.obtenerRecursoTipoMoto());
        PaginaDialogoVehiculo.editarCajaTexto(PaginaDialogoVehiculo.obtenerRecursoCajaTextoPlaca(), placa);
        PaginaDialogoVehiculo.editarCajaTexto(PaginaDialogoVehiculo.obtenerRecursoCajaTextoCilindraje(), cilindraje);
        //Assert
        PaginaDialogoVehiculo.clicBoton(PaginaDialogoVehiculo.obtenerRecursoButonAgregarVehiculo());
    }

}
