package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegisterModal {

    WebDriver driver;

    public RegisterModal(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "sign-up-name")
    WebElement signUpNameField;

    @FindBy(id = "sign-up-username")
    WebElement signUpEmailField;

    @FindBy(id = "birth-year")
    WebElement birthYearDropDown;

    @FindBy(id = "birth-month")
    WebElement birthMonthDropDown;

    @FindBy(id = "birth-date")
    WebElement birthDateDropDown;

    @FindBy(id = "sign-up-msisdn")
    WebElement signUpMobileNumField;

    @FindBy(id = "sign-up-password")
    WebElement signUpPasswordField;

    @FindBy(id = "sign-up-button")
    WebElement signUpButton;

    @FindBy(id = "sign-up-form-error")
    WebElement warningMessageRequiredFieldsEmpty;

    public void enterSignUpName(String signUpNameText){
        signUpNameField.sendKeys(signUpNameText);
    }

    public void enterSignUpEmail(String emailText){
        signUpEmailField.sendKeys(emailText);
    }

    public void selectBirthYear(String birthYearValue){
        Select dropdown = new Select(birthYearDropDown);
        dropdown.selectByValue(birthYearValue);
        dropdown.getFirstSelectedOption().click();
    }

    public void selectBirthMonth(String birthMonthValue){
        Select dropdown = new Select(birthMonthDropDown);
        dropdown.selectByValue(birthMonthValue);
        dropdown.getFirstSelectedOption().click();
    }

    public void selectBirthDate(String birthDateValue){
        Select dropdown = new Select(birthDateDropDown);
        dropdown.selectByValue(birthDateValue);
        dropdown.getFirstSelectedOption().click();
    }

    public void enterSignUpMobileNumber(String signUpMobileNumText){
        signUpMobileNumField.sendKeys(signUpMobileNumText);
    }

    public void enterSignUpPassword(String signUpPasswordText){
        signUpPasswordField.sendKeys(signUpPasswordText);
    }

    public HomePage clickOnSignUpButton(){
        signUpButton.click();
        return new HomePage(driver);
    }

    public String getWarningMessageForRequiredFieldsEmpty(){
        return warningMessageRequiredFieldsEmpty.getText();
    }








}
