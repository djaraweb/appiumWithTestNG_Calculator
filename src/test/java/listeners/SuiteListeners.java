package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import utilities.Logs;

public class SuiteListeners implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        Logs.info("Comenzando la suite : %s", suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        Logs.info("Terminando la suite : %s", suite.getName());
    }
}
