package automation;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTests;
import utilities.Helpers;
import utilities.Logs;

public class Tarea2Tests extends BaseTests {

    @Override
    protected void setNameApkTesting() {
        BaseTests.nameApkTesting = "calculator";
    }

    @Test(groups = {regression, smoke})
    public void ejercicio1Test() {
        Logs.info("Hacer clic en DEG (parte superior izquierda)");
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/mode")).click();

        Logs.info("Verificar que arriba izquierda ahora diga RAD");
        final var radianMode = driver.findElement(AppiumBy.id("com.google.android.calculator:id/mode"));
        softAssert.assertTrue(radianMode.isDisplayed());
        softAssert.assertEquals(radianMode.getText(), "RAD");
        softAssert.assertAll();

        Logs.info("Verificar que en el menú azul diga DEG");
        final var switchToDegres = driver.findElement(AppiumBy.accessibilityId("cambiar a grados"));
        softAssert.assertTrue(switchToDegres.isDisplayed());
        softAssert.assertEquals(switchToDegres.getText(), "DEG");
        softAssert.assertAll();
    }

    @Test(groups = {regression, smoke})
    public void ejercicio2Test() {
        Logs.info("Hacer clic en el valor de PI");
        driver.findElement(AppiumBy.accessibilityId("pi")).click();

        Logs.info("Presionar el boton de igual para obtener el resultado");
        driver.findElement(AppiumBy.accessibilityId("igual a")).click();

        Logs.info("Verificar que el número tenga los mismos primeros 3 decimales que el valor de PI en Java");
        final var result = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final"));

        softAssert.assertTrue(result.isDisplayed());
        softAssert.assertEquals(result.getText().substring(2, 5), String.valueOf(Math.PI).substring(2, 5));
        softAssert.assertAll();
    }

    @Test(groups = {regression, smoke})
    public void ejercicio3Test() {
        Logs.info("Generar 1 número aleatorio entre 3 y 7");
        final var numeroAleatorio = faker.number().numberBetween(3, 7);
        final var factorial = Helpers.getFactorial(numeroAleatorio);
        Logs.info("El factorial del numero (%d) es: %d ", numeroAleatorio, factorial);

        Logs.info("Escribir el número con el simbolo de !(factorial)");
        new Actions(driver)
                .sendKeys(String.valueOf(numeroAleatorio))
                .keyDown(Keys.SHIFT)
                .sendKeys("1") // !
                .perform();

        Logs.info("Presionar el boton de igual para obtener el resultado");
        driver.findElement(AppiumBy.accessibilityId("igual a")).click();

        Logs.info("Verificar que el número que salga en la calculadora sea igual al del punto 2");
        final var result = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final"));

        softAssert.assertTrue(result.isDisplayed());
        softAssert.assertEquals(Integer.parseInt(result.getText()), factorial);
        softAssert.assertAll();
    }

    @Test(groups = {regression, smoke})
    public void ejercicio4Test() {
        Logs.info("Generar 2 números enteros entre 100 y 250");
        final var numeroAleatorio1 = faker.number().numberBetween(100, 250);
        final var numeroAleatorio2 = faker.number().numberBetween(100, 250);
        final var resultSuma = numeroAleatorio1 + numeroAleatorio2;

        Logs.info("Escribir el 1er número: %d", numeroAleatorio1);
        Actions actions = new Actions(driver);
        actions
                .sendKeys(String.valueOf(numeroAleatorio1))
                .perform();

        Logs.info("Hacer click en el simbolo de +");
        driver.findElement(AppiumBy.accessibilityId("más")).click();

        Logs.info("Escribir el 2do número: %d", numeroAleatorio2);
        actions
                .sendKeys(String.valueOf(numeroAleatorio2))
                .perform();

        Logs.info("Presionar el boton de igual para obtener el resultado");
        driver.findElement(AppiumBy.accessibilityId("igual a")).click();

        Logs.info("Verificar que el número que salga en la calculadora sea igual al del punto 2 : %d", resultSuma);
        final var result = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final"));

        softAssert.assertTrue(result.isDisplayed());
        softAssert.assertEquals(Integer.parseInt(result.getText()), resultSuma);
        softAssert.assertAll();
    }

    @Test(groups = {regression, smoke})
    public void ejercicio5Test() {
        Logs.info("Generar 2 números enteros entre 10 y 100");
        final var numeroAleatorio1 = faker.number().numberBetween(10, 100);
        final var numeroAleatorio2 = faker.number().numberBetween(10, 100);
        final var resultMultiply = numeroAleatorio1 * numeroAleatorio2;

        Logs.info("Escribir el 1er número: %d", numeroAleatorio1);
        Actions actions = new Actions(driver);
        actions
                .sendKeys(String.valueOf(numeroAleatorio1))
                .perform();

        Logs.info("Hacer click en el simbolo de *");
        driver.findElement(AppiumBy.accessibilityId("×")).click();

        Logs.info("Escribir el 2do número: %d", numeroAleatorio2);
        actions
                .sendKeys(String.valueOf(numeroAleatorio2))
                .perform();

        Logs.info("Presionar el boton de igual para obtener el resultado");
        driver.findElement(AppiumBy.accessibilityId("igual a")).click();

        Logs.info("Verificar que el número que salga en la calculadora sea igual al del punto 2: %d", resultMultiply);
        final var result = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final"));

        softAssert.assertTrue(result.isDisplayed());
        softAssert.assertEquals(Integer.parseInt(result.getText()), resultMultiply);
        softAssert.assertAll();
    }

    @Test(groups = {regression, smoke})
    public void ejercicio6Test() {

        Logs.info("Hacer clic en los 3 puntitos");
        driver.findElement(AppiumBy.accessibilityId("Más opciones")).click();

        Logs.info("Presionar History");
        driver.findElement(AppiumBy.androidUIAutomator("text(\"Historial\")")).click();

        Logs.info("Verificar que salga la página de History");
        Assert.assertTrue(driver.findElement(AppiumBy.androidUIAutomator("text(\"Historial\")")).isDisplayed());

        Logs.info("Presionar la flecha de atrás de arriba izquierda");
        driver.findElement(AppiumBy.accessibilityId("Navegar hacia arriba")).click();

        Logs.info("Verificar que regrese a la página principal");
        Assert.assertTrue(driver.findElement(AppiumBy.id("com.google.android.calculator:id/mode")).isDisplayed());
    }

    @Test(groups = {regression, smoke})
    public void ejercicio7Test() {

        Logs.info("Hacer clic en los 3 puntitos");
        driver.findElement(AppiumBy.accessibilityId("Más opciones")).click();

        Logs.info("Presionar History");
        driver.findElement(AppiumBy.androidUIAutomator("text(\"Historial\")")).click();

        Logs.info("Verificar que salga la página de History");
        Assert.assertTrue(driver.findElement(AppiumBy.androidUIAutomator("text(\"Historial\")")).isDisplayed());

        Logs.info("Presionar el botón atrás del celular");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        Logs.info("Verificar que regrese a la página principal");
        Assert.assertTrue(driver.findElement(AppiumBy.id("com.google.android.calculator:id/mode")).isDisplayed());
    }
}
