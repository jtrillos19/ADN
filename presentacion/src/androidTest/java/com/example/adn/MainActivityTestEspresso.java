package com.example.adn;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

public class MainActivityTestEspresso {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTestEspresso() {
        ViewInteraction button = onView(
                allOf(withId(R.id.btnIngresarVehiculo), withText("INGRESAR VEHICULO"),
                        withParent(allOf(withId(R.id.content),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        pressBack();

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btnIngresarVehiculo), withText("Ingresar Vehiculo"),
                        childAtPosition(
                                allOf(withId(R.id.content),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.btn_cancelar), withText("Cancelar"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btnIngresarVehiculo), withText("Ingresar Vehiculo"),
                        childAtPosition(
                                allOf(withId(R.id.content),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction materialRadioButton = onView(
                allOf(withId(R.id.tipoCarro), withText("Carro"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        materialRadioButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.placa),
                        childAtPosition(
                                allOf(withId(R.id.contenedorPlaca),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("wert56"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.btn_agregar), withText("Agregar"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.btnIngresarVehiculo), withText("Ingresar Vehiculo"),
                        childAtPosition(
                                allOf(withId(R.id.content),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction materialRadioButton2 = onView(
                allOf(withId(R.id.tipoMoto), withText("Moto"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        materialRadioButton2.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.placa),
                        childAtPosition(
                                allOf(withId(R.id.contenedorPlaca),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("ertf45"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.cilindraje), withText("0"),
                        childAtPosition(
                                allOf(withId(R.id.contenedorCilindraje),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("200"));

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.cilindraje), withText("200"),
                        childAtPosition(
                                allOf(withId(R.id.contenedorCilindraje),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(closeSoftKeyboard());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.btn_agregar), withText("Agregar"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.btnIngresarVehiculo), withText("Ingresar Vehiculo"),
                        childAtPosition(
                                allOf(withId(R.id.content),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        materialButton7.perform(click());

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.btn_cancelar), withText("Cancelar"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        materialButton8.perform(click());

        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.btn_cobrar), withText("Cobrar"),
                        childAtPosition(
                                allOf(withId(R.id.contentBtnViewPost),
                                        childAtPosition(
                                                withId(R.id.contentCard),
                                                3)),
                                0),
                        isDisplayed()));
        materialButton9.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
