package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class LoginModal {

    WebDriver driver;

    private ElementUtils elementUtils;

    public LoginModal(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        elementUtils = new ElementUtils(driver);
    }

    //Objects
    @FindBy(id="sign-in-username")
    WebElement emailAddressField;

    @FindBy(id="sign-in-password")
    WebElement passwordField;

    @FindBy(id="sign-in-button")
    WebElement signInButton;

    @FindBy(id = "sign-in-form-error-text")
    WebElement warningMessage;

    @FindBy(id="sign-in-form-error")
    WebElement warningMessageRequiredFieldsEmpty;

    @FindBy(css = "[data-target='#sign-up-modal'] > b")
    WebElement signUpOption;

    //Actions
    public void enterEmailAddress(String emailText){
        elementUtils.typeTextIntoElement(emailAddressField, emailText);
    }

    public void enterPassword(String passwordText){
        elementUtils.typeTextIntoElement(passwordField, passwordText);
    }

    public HomePage clickOnSignInButton(){
        elementUtils.clickOnElement(signInButton);
        return new HomePage(driver);
    }

    public String getWarningMessage(){
        return elementUtils.getAttributeFromElement(warningMessage, "innerHTML");
    }

    public String getWarningMessageForRequiredFieldsEmpty(){
        return elementUtils.getTextFromElement(warningMessageRequiredFieldsEmpty);
    }

    public RegisterModal navigateToRegisterModal(){
        elementUtils.clickOnElement(signUpOption);
        return new RegisterModal(driver);
    }

}
