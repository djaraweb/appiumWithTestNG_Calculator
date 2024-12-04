package automation;

import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTests;
import utilities.Gestures;
import utilities.Helpers;
import utilities.Logs;

public class Tarea3Tests extends BaseTests {

    private void hacerTab(String cadena) {
        char[] arrayChar = cadena.toCharArray();
        for (var c : arrayChar) {
            String elementText = String.format("text(\"%s\")", c);
            Gestures.tab(
                    driver.findElement(AppiumBy.androidUIAutomator(elementText))
            );
        }
    }

    @Test(groups = {regression, smoke})
    public void ejercicio1Test() {
        Logs.info("Declarar un método que haga tap en un número/símbolo que se le pasa como string. Por\n" +
                "ejemplo: tap(“125+14=”) hace tap en 1, 2, 5, +, 1, 4, =");

        hacerTab("125+14=");
    }

    @Test(groups = {regression, smoke})
    public void ejercicio2Test() {
        Logs.info("Hacer tab en DEG (parte superior izquierda)");
        Gestures.tab(driver.findElement(AppiumBy.id("com.google.android.calculator:id/mode")));

        Logs.info("Verificar que arriba izquierda ahora diga RAD");
        final var radianMode = driver.findElement(AppiumBy.id("com.google.android.calculator:id/mode"));
        softAssert.assertTrue(radianMode.isDisplayed());
        softAssert.assertEquals(radianMode.getText(), "RAD");
        softAssert.assertAll();

        Logs.info("Verificar que en el menú azul diga DEG");
        final var switchToDegres = driver.findElement(AppiumBy.accessibilityId("switch to degrees"));
        softAssert.assertTrue(switchToDegres.isDisplayed());
        softAssert.assertEquals(switchToDegres.getText(), "DEG");
        softAssert.assertAll();
    }

    @Test(groups = {regression, smoke})
    public void ejercicio3Test() {
        Logs.info("Hacer clic en el valor de PI");
        Gestures.tab(driver.findElement(AppiumBy.accessibilityId("pi")));

        Logs.info("Verificar que el número tenga los mismos primeros 3 decimales que el valor de PI en Java");
        final var result = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_preview"));

        softAssert.assertTrue(result.isDisplayed());
        softAssert.assertEquals(result.getText().substring(2, 5), String.valueOf(Math.PI).substring(2, 5));
        softAssert.assertAll();
    }

    @Test(groups = {regression, smoke})
    public void ejercicio4Test() {
        Logs.info("Generar 1 número aleatorio entre 3 y 7");
        final var numeroAleatorio = faker.number().numberBetween(3, 7);
        final var factorial = Helpers.getFactorial(numeroAleatorio);
        Logs.info("El factorial del numero (%d) es: %d ", numeroAleatorio, factorial);

        Logs.info("Hacer tap usando el String “X!=” donde X es el número generado");
        hacerTab(String.format("%d!=", numeroAleatorio));

        Logs.info("Verificar que el número que salga en la calculadora sea igual al del punto 2");
        final var result = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final"));

        softAssert.assertTrue(result.isDisplayed());
        softAssert.assertEquals(Integer.parseInt(result.getText()), factorial);
        softAssert.assertAll();
    }

    @Test(groups = {regression, smoke})
    public void ejercicio5Test() {
        Logs.info("Generar 2 números enteros entre 1 y 9");
        final var numeroAleatorio1 = faker.number().numberBetween(1, 9);
        final var numeroAleatorio2 = faker.number().numberBetween(1, 9);
        final var resultSuma = numeroAleatorio1 + numeroAleatorio2;

        Logs.info("Hacer tap usando el String “X+Y=” donde X,Y son los números generados");
        Logs.info("El 1er número: %d", numeroAleatorio1);
        Logs.info("El 2do número: %d", numeroAleatorio2);

        hacerTab(String.format("%d+%d=", numeroAleatorio1, numeroAleatorio2));

        Logs.info("Verificar que el número que salga en la calculadora sea igual al del punto 2 : %d", resultSuma);
        final var result = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final"));

        softAssert.assertTrue(result.isDisplayed());
        softAssert.assertEquals(Integer.parseInt(result.getText()), resultSuma);
        softAssert.assertAll();
    }

    @Test(groups = {regression, smoke})
    public void ejercicio6Test() {
        Logs.info("Generar un número entero entre 5000 y 10000");
        final var numeroAleatorio = faker.number().numberBetween(5000, 10000);

        Logs.info("El número generado es : %d", numeroAleatorio);
        Logs.info("Escribimos el Nro usando tab");
        hacerTab(String.valueOf(numeroAleatorio));

        Logs.info("Hacer doble tap donde está escrito el número para seleccionarlo");
        Gestures.doubleTab(driver.findElement(AppiumBy.id("com.google.android.calculator:id/formula")));

        Logs.info("Hacemos tab en el boton de borrar");
        Gestures.tab(driver.findElement(AppiumBy.accessibilityId("delete")));

        final var result = driver.findElement(AppiumBy.accessibilityId("No formula"));

        softAssert.assertTrue(result.isDisplayed());
        softAssert.assertEquals(result.getText(), "");
        softAssert.assertAll();
    }

    @Test(groups = {regression, smoke})
    public void ejercicio7Test() {
        Logs.info("Generar un número entero entre 5000 y 10000");
        final var numeroAleatorio = faker.number().numberBetween(5000, 10000);

        Logs.info("El número generado es : %d", numeroAleatorio);
        Logs.info("Escribimos el Nro usando tab");
        hacerTab(String.valueOf(numeroAleatorio));

        Logs.info("Hacemos long tab en el boton de borrar");
        Gestures.longTab(driver.findElement(AppiumBy.accessibilityId("delete")));

        final var result = driver.findElement(AppiumBy.accessibilityId("No formula"));

        softAssert.assertTrue(result.isDisplayed());
        softAssert.assertEquals(result.getText(), "");
        softAssert.assertAll();
    }

    @Test
    public void ejercicio8Test() {
        Logs.info("Obtenemos el canvas desde donde se realizara el swipe");
        final var canvan = driver.findElement(AppiumBy.id("com.google.android.calculator:id/drag_layout"));

        Logs.info("Realizamos el swipe vertical");
        Gestures.swipeVertical(50, 5, 90, canvan);

        Logs.info("Verificamos que exista el elemento History");
        final var element = driver.findElement(AppiumBy.androidUIAutomator("text(\"No History\")"));
        Assert.assertTrue(element.isDisplayed());

    }
}
