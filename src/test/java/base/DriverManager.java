package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import utils.CommonUtils;
import utils.PropertyReader;

import java.time.Duration;

public class DriverManager {

    static WebDriver driver = null;
    
    public void initializeBrowser(String browserName) {

        if (browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if ((browserName.equalsIgnoreCase("edge"))) {
            driver = new EdgeDriver();
        } else if ((browserName.equalsIgnoreCase("safari"))) {
            driver = new SafariDriver();            
        } else {
            System.out.println("--- Incorrect Browser Name! ---");
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(CommonUtils.PAGE_LOAD_TIME));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(CommonUtils.IMPLICIT_WAIT_TIME));
        //return driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

}
