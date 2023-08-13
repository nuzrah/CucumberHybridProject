package stepdefinitions;

import base.DriverManager;
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
import utils.CommonUtils;

import java.time.Duration;


public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;
    private String actualWarningMessage;
    private String expectedWarningMessage;
    private HomePage homePage;
    private LoginModal loginModal;
    private CommonUtils commonUtils;
    private DriverManager driverManager = new DriverManager();

    @Given("User navigates to login modal")
    public void userNavigatesToLoginModal() {
        driver = driverManager.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        homePage = new HomePage(driver);
        loginModal = homePage.clickOnSignInOption();
        //driver.findElement(By.xpath("//li/a[contains(.,'Sign In')]")).click();
    }

    @When("^User enters valid email address (.+)$")
    public void userEntersValidEmailAddress(String emailText) {
        loginModal.enterEmailAddress(emailText);
        //driver.findElement(By.id("sign-in-username")).sendKeys(emailText);
    }

    @And("^User enters valid password (.+) into the password field$")
    public void userEntersValidPasswordIntoThePasswordField(String passwordText) {
        loginModal.enterPassword(passwordText);
        //driver.findElement(By.id("sign-in-password")).sendKeys(passwordText);
    }

    @And("User clicks on the Sign In button")
    public void userClicksOnTheSignInButton() {
        loginModal.clickOnSignInButton();
        //driver.findElement(By.cssSelector("#sign-in-button")).click();
    }

    @Then("User is successfully logged in")
    public void userIsSuccessfullyLoggedIn() {
        //Assert.assertTrue(driver.findElement(By.cssSelector("#user-profile-dropdown")).isDisplayed());
        Assert.assertTrue(homePage.getDisplayStatusOfSignedInUserName());
    }

    @When("User enters invalid email address")
    public void userEntersInvalidEmailAddress() {
        //driver.findElement(By.id("sign-in-username")).sendKeys(generateEmailWithTimeStamp());
        commonUtils = new CommonUtils();
        loginModal.enterEmailAddress(commonUtils.generateEmailWithTimeStamp());
    }

    @And("User enters invalid password {string} into the password field")
    public void userEntersInvalidPasswordIntoThePasswordField(String invalidPasswordText) {
        loginModal.enterPassword(invalidPasswordText);
    }

    @Then("User is prompted with a Warning Message related to Consumer not found")
    public void userIsPromptedWithAWarningMessageRelatedToConsumerNotFound() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-in-form-error-text")));
        actualWarningMessage = loginModal.getWarningMessage();
        expectedWarningMessage = "Sign in Failed :  Consumer user not found";
        Assert.assertEquals(actualWarningMessage,expectedWarningMessage,"\nExpected error message: |"+expectedWarningMessage+"| \nActual error message  : |"+actualWarningMessage+"|\n");
        //Assert.assertEquals("\nExpected error message: |"+expectedWarningMessage+"| \nActual error message  : |"+actualWarningMessage+"|\n",expectedWarningMessage,actualWarningMessage);
    }

    @Then("User is prompted with a Warning Message related to Authentication Failed")
    public void userIsPromptedWithAWarningMessageRelatedToAuthenticationFailed() {
        actualWarningMessage = loginModal.getWarningMessage();
        expectedWarningMessage = "Sign in Failed :  Authentication Failed";
        Assert.assertEquals(actualWarningMessage,expectedWarningMessage,"\nExpected error message: |"+expectedWarningMessage+"| \nActual error message  : |"+actualWarningMessage+"|\n");
        //Assert.assertEquals("\nExpected error message: |"+expectedWarningMessage+"| \nActual error message  : |"+actualWarningMessage+"|\n",expectedWarningMessage,actualWarningMessage);

    }

    @When("User does not enter email into email field")
    public void userDoesNotEnterEmailIntoEmailField() {
        loginModal.enterEmailAddress("");
    }

    @And("User does not enter password into password field")
    public void userDoesNotEnterPasswordIntoPasswordField() {
        loginModal.enterPassword("");
    }

    @Then("User is prompted with a Warning Message related to Required Fields being empty")
    public void userIsPromptedWithAWarningMessageRelatedToRequiredFieldsBeingEmpty() {
        actualWarningMessage = loginModal.getWarningMessageForRequiredFieldsEmpty();
        expectedWarningMessage = "  Required Fields are Empty ";
        Assert.assertEquals(actualWarningMessage,expectedWarningMessage,"\nExpected error message: |"+expectedWarningMessage+"| \nActual error message  : |"+actualWarningMessage+"|\n");
        //Assert.assertEquals("\nExpected error message: |"+expectedWarningMessage+"| \nActual error message  : |"+actualWarningMessage+"|\n",expectedWarningMessage,actualWarningMessage);
    }
}
