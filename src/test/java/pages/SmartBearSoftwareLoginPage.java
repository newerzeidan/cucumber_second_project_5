package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SmartBearSoftwareLoginPage {
    public SmartBearSoftwareLoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "ctl00_MainContent_username")
    public WebElement usernameInputBox;

    @FindBy(id = "ctl00_MainContent_password")
    public WebElement passwordInputBox;

    @FindBy(id = "ctl00_MainContent_login_button")
    public WebElement loginBtn;

    @FindBy(id = "ctl00_MainContent_status")
    public WebElement invalidLoginMessage;
}