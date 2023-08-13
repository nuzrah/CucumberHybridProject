package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CommonUtils {

    public static final int IMPLICIT_WAIT_TIME=10;
    public static final int PAGE_LOAD_TIME=180;
    public static final int EXPLICIT_WAIT_BASIC_TIME=30;

    public String generateTimeStamp() {
        Date date = new Date();
        return date.toString().replace(" ","_").replace(":", "_");
    }

    public String generateEmailWithTimeStamp() {
        return "test-automation-"+generateTimeStamp()+"@applova.io";
    }

    public String generatePhoneNumberWithTimeStamp() {
        Date date = new Date();
        //SimpleDateFormat ft = new SimpleDateFormat ("yyMMdhmmss");
        SimpleDateFormat ft = new SimpleDateFormat ("hhmmss");
        String timestamp = ft.format(date);
        String mobileNum = "2812" + timestamp;
        //System.out.println("Current Date: " + timestamp);
        return mobileNum;
    }

    public String captureScreenshot(WebDriver driver, String testName){
        File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationScreenshotPath = System.getProperty("user.dir")+"/screenshots/"+testName+".png";
        try {
            FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return destinationScreenshotPath;
    }


    private By getByFromElement(WebElement element) {
        By by = null;
        //[[ChromeDriver: chrome on XP (d85e7e220b2ec51b7faf42210816285e)] -> xpath: //input[@title='Search']]
        String[] pathVariables = (element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "")).split(":");

        String selector = pathVariables[0].trim();
        String value = pathVariables[1].trim();

        switch (selector) {
            case "id":
                by = By.id(value);
                break;
            case "className":
                by = By.className(value);
                break;
            case "tagName":
                by = By.tagName(value);
                break;
            case "xpath":
                by = By.xpath(value);
                break;
            case "cssSelector":
            case "css":
                by = By.cssSelector(value);
                break;
            case "linkText":
                by = By.linkText(value);
                break;
            case "name":
                by = By.name(value);
                break;
            case "partialLinkText":
                by = By.partialLinkText(value);
                break;
            default:
                throw new IllegalStateException("locator : |" + selector + "| not found!!!");
        }
        return by;
    }

}
