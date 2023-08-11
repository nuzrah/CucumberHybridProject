package stepdefinitions;

import base.DriverManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginModal;
import pages.RegisterModal;
import utils.CommonUtils;

import java.time.Duration;
import java.util.Map;

public class RegisterTest {

    WebDriver driver;
    String actualWarningMessage;
    String expectedWarningMessage;
    private HomePage homePage;
    private LoginModal loginModal;
    private RegisterModal registerModal;
    private CommonUtils commonUtils = new CommonUtils();
    private DriverManager driverManager = new DriverManager();

    @Given("User navigates to the Register Modal")
    public void userNavigatesToTheRegisterModal() {
        driver = driverManager.getDriver();
        WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));

        homePage = new HomePage(driver);
        loginModal = homePage.clickOnSignInOption();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-in-modal-label")));

        registerModal = loginModal.navigateToRegisterModal();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-up-modal-label")));
    }

    @When("User enters the details into register mandatory fields")
    public void userEntersTheDetailsIntoRegisterMandatoryFields(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);

//        driver.findElement(By.id("sign-up-name")).sendKeys(dataMap.get("firstName"));
//        driver.findElement(By.id("sign-up-username")).sendKeys(generateEmailWithTimeStamp());
//
//        birthYearDropdown = driver.findElement(By.id("birth-year"));
//        Select dropdown = new Select(birthYearDropdown);
//        dropdown.selectByValue(dataMap.get("birthYear"));
//        dropdown.getFirstSelectedOption().click();
//
//        birthMonthDropdown = driver.findElement(By.id("birth-month"));
//        dropdown = new Select(birthMonthDropdown);
//        dropdown.selectByValue(dataMap.get("birthMonth"));
//        dropdown.getFirstSelectedOption().click();
//
//        birthDateDropdown = driver.findElement(By.id("birth-date"));
//        dropdown = new Select(birthDateDropdown);
//        dropdown.selectByValue(dataMap.get("birthDate"));
//        dropdown.getFirstSelectedOption().click();
//
//        driver.findElement(By.id("sign-up-msisdn")).sendKeys(generatePhoneNumberWithTimeStamp());
//        driver.findElement(By.id("sign-up-password")).sendKeys(dataMap.get("password"));
        registerModal.enterSignUpName(dataMap.get("firstName"));
        registerModal.enterSignUpEmail(commonUtils.generateEmailWithTimeStamp());
        registerModal.selectBirthYear(dataMap.get("birthYear"));
        registerModal.selectBirthMonth(dataMap.get("birthMonth"));
        registerModal.selectBirthDate(dataMap.get("birthDate"));
        registerModal.enterSignUpMobileNumber(commonUtils.generatePhoneNumberWithTimeStamp());
        registerModal.enterSignUpPassword(dataMap.get("password"));
    }

    @And("User clicks on Sign Up button")
    public void userClicksOnSignUpButton() {
        //driver.findElement(By.id("sign-up-button")).click();
        registerModal.clickOnSignUpButton();
    }

    @Then("User account gets created successfully")
    public void userAccountGetsCreatedSuccessfully() {
        Assert.assertTrue(driver.findElement(By.cssSelector("#user-profile-dropdown")).isDisplayed());
        //Assert.assertTrue(homePage.getDisplayStatusOfSignedInUserName());
    }

    @When("User does not enter any details into any fields")
    public void userDoesNotEnterAnyDetailsIntoAnyFields() {
        registerModal.enterSignUpName("");
        registerModal.enterSignUpEmail("");
        registerModal.enterSignUpMobileNumber("");
        registerModal.enterSignUpPassword("");
    }

    @Then("User is prompted with a warning message related to Required Fields being empty")
    public void userIsPromptedWithAWarningMessageRelatedToRequiredFieldsBeingEmpty() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-up-form-error")));

        actualWarningMessage = registerModal.getWarningMessageForRequiredFieldsEmpty();
        expectedWarningMessage = " Required Fields are Empty";
        Assert.assertEquals(actualWarningMessage,expectedWarningMessage,"\nExpected error message: |"+expectedWarningMessage+"| \nActual error message  : |"+actualWarningMessage+"|\n");
        //Assert.assertEquals("\nExpected error message: |"+expectedWarningMessage+"| \nActual error message  : |"+actualWarningMessage+"|\n", expectedWarningMessage, actualWarningMessage);
    }

}
