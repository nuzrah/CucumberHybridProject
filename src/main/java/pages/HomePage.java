package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonUtils;
import utils.ElementUtils;

public class HomePage {
    WebDriver driver;
    private CommonUtils commonUtils = new CommonUtils();
    private ElementUtils elementUtils;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        elementUtils = new ElementUtils(driver);
    }

    @FindBy(xpath="//li/a[contains(.,'Sign In')]")
    private WebElement signInOptionButton;

    @FindBy(id="user-profile-dropdown")
    private WebElement signedInUserNameDropDown;

    public LoginModal clickOnSignInOption(){
        elementUtils.clickOnElement(signInOptionButton);
        return new LoginModal(driver);
    }

    public boolean getDisplayStatusOfSignedInUserName(){
        return elementUtils.displayStatusOfElement(signedInUserNameDropDown);
    }

}
