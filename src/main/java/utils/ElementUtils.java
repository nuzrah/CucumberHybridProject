package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementUtils {

    WebDriver driver;

    public ElementUtils(WebDriver driver){
        this.driver = driver;

    }

    public WebElement waitForElement(WebElement element) {
        WebElement webElement = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(CommonUtils.EXPLICIT_WAIT_BASIC_TIME));
            webElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e){
            e.printStackTrace();
        }
        return webElement;
    }

    public void clickOnElement(WebElement element) {
        WebElement webElement = waitForElement(element);
        webElement.click();
    }

    public void typeTextIntoElement(WebElement element, String textToBeTyped) {
        WebElement webElement = waitForElement(element);
        webElement.click();
        webElement.clear();
        webElement.sendKeys(textToBeTyped);
    }

    public void selectDropDownOption(WebElement element, String dropDownOption) {
        WebElement webElement = waitForElement(element);
        Select dropdown = new Select(webElement);
        dropdown.selectByVisibleText(dropDownOption);
        dropdown.getFirstSelectedOption().click();
    }

    public void acceptAlert() {
        Alert alert = waitForAlert();
        alert.accept();
    }

    public void dismissAlert() {
        Alert alert = waitForAlert();
        alert.dismiss();
    }

    public Alert waitForAlert() {
        Alert alert = null;

        try {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(CommonUtils.EXPLICIT_WAIT_BASIC_TIME));
            alert = wait.until(ExpectedConditions.alertIsPresent());
        }catch(Throwable e) {
            e.printStackTrace();
        }

        return alert;
    }

    public void mouseHoverAndClick(WebElement element) {
        WebElement webElement = waitForVisibilityOfElement(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).click().build().perform();
    }

    public WebElement waitForVisibilityOfElement(WebElement element) {
        WebElement webElement = null;

        try {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(CommonUtils.EXPLICIT_WAIT_BASIC_TIME));
            webElement = wait.until(ExpectedConditions.visibilityOf(element));
        }catch(Throwable e) {
            e.printStackTrace();
        }

        return webElement;
    }

    public void javaScriptClick(WebElement element) {
        WebElement webElement = waitForVisibilityOfElement(element);
        JavascriptExecutor jse = ((JavascriptExecutor)driver);
        jse.executeScript("arguments[0].click();",webElement);
    }

    public void javaScriptType(WebElement element,String textToBeTyped) {
        WebElement webElement = waitForVisibilityOfElement(element);
        JavascriptExecutor jse = ((JavascriptExecutor)driver);
        jse.executeScript("arguments[0].value='"+textToBeTyped+"'",webElement);
    }

    public String getTextFromElement(WebElement element) {
        WebElement webElement = waitForElement(element);
        return webElement.getText();
    }

    public String getAttributeFromElement(WebElement element, String attribute) {
        WebElement webElement = waitForElement(element);
        return webElement.getAttribute(attribute);
    }

    public boolean displayStatusOfElement(WebElement element) {
        try {
            WebElement webElement = waitForVisibilityOfElement(element);
            return webElement.isDisplayed();
        }catch(Throwable e) {
            return false;
        }
    }

}
