package hooks;

import base.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.PropertyReader;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Hooks {

    WebDriver driver;

    @Before(order = 0)
    public void beforeScenario(){
        System.out.println("\n--------------------------------[ Start of Scenario ]---------------------------------\n");
    }

    @Before(order = 1)
    public void setUp() {
        //PropertyReader prop = new PropertyReader();
        Properties prop = new PropertyReader().initializeProperties();

        DriverManager driverManager = new DriverManager();
        driverManager.initializeBrowser(prop.getProperty("browser.name"));


        //driverManager.initializeBrowser(prop.getProperty("config", "browser.name"));

        driver = driverManager.getDriver();

        //driver.get(prop.getProperty("config", "webstore.url"));
        driver.get(prop.getProperty("webstore.url"));
    }

    @After
    public void tearDown(Scenario scenario) {

        String scenarioName = scenario.getName().replace(" ", "_");
        if (scenario.isFailed()){
            byte[] srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(srcScreenshot, "image/png", scenarioName);
        }

        System.out.println("\n---------------------------------[ End of Scenario ]----------------------------------\n");

        driver.close();
        driver.quit();
    }

}
