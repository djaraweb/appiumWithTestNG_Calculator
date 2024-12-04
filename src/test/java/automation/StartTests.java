package automation;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTests;
import utilities.Logs;

public class StartTests extends BaseTests {

    @Override
    protected void setNameApkTesting() {
        BaseTests.nameApkTesting = "calculator";
    }

    @Test(groups = {regression, smoke})
    public void initAppTest() {
        Logs.info("Ejercicio2: Verificando que se cargue la Apk de Calculator");
        sleep(3000);
        Assert.assertEquals("Locator1","locator1");
    }
}
