package steps;

import com.github.javafaker.Faker;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.SmartBearSoftwareLoginPage;
import pages.SmartBearSoftwareWebOrdersPage;
import utilities.CONSTANTS;
import utilities.Driver;
import utilities.TableHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmartBearSoftwareLoginSteps {
    WebDriver driver;
    WebElement table;
    Faker faker;
    SmartBearSoftwareLoginPage smartBearSoftwareLoginPage;
    SmartBearSoftwareWebOrdersPage smartBearSoftwareWebOrdersPage;

    @Before
    public void setup() {
        driver = Driver.getDriver();
        faker = new Faker();
        smartBearSoftwareLoginPage = new SmartBearSoftwareLoginPage();
        smartBearSoftwareWebOrdersPage = new SmartBearSoftwareWebOrdersPage();
    }

    @Given("user is on {string}")
    public void userIsOn(String url) {
        driver.get(url);
    }

    @When("user enters username as {string}")
    public void userEntersUsernameAs(String username) {
        smartBearSoftwareLoginPage.usernameInputBox.sendKeys(username);
    }

    @And("user enters password as {string}")
    public void userEntersPasswordAs(String password) {
        smartBearSoftwareLoginPage.passwordInputBox.sendKeys(password);
    }

    @And("user clicks on Login button")
    public void userClicksOnLoginButton() {
        smartBearSoftwareLoginPage.loginBtn.click();
    }

    @Then("user should see {string} message")
    public void userShouldSeeMessage(String expectedErrorMsg) {
        Assert.assertTrue(smartBearSoftwareLoginPage.invalidLoginMessage.isDisplayed());
        Assert.assertEquals(expectedErrorMsg, smartBearSoftwareLoginPage.invalidLoginMessage.getText());
    }


    @Then("user should be routed to {string}")
    public void userShouldBeRoutedTo(String expectedURL) {
        Assert.assertEquals(expectedURL, driver.getCurrentUrl());
    }

    @And("validate below menu items are displayed")
    public void validateBelowMenuItemsAreDisplayed(DataTable data) {
        for(int i = 0; i < data.asList().size(); i++) {
            Assert.assertTrue(smartBearSoftwareWebOrdersPage.menuItemsList.get(i).isDisplayed());
            Assert.assertEquals(data.asList().get(i), smartBearSoftwareWebOrdersPage.menuItemsList.get(i).getText());
        }
    }

    @When("user clicks on {string} button")
    public void userClicksOnButton(String btn) {
        smartBearSoftwareWebOrdersPage.selectBtnByVisibleText(btn);
    }


    @Then("all rows should be checked")
    public void allRowsShouldBeChecked() {
        table = smartBearSoftwareWebOrdersPage.table;
        for(int i = 1; i < TableHandler.getRowCount(table); i++) {
            Assert.assertTrue(TableHandler.getCheckbox(table, i, 0).isSelected());
        }
    }

    @Then("all rows should be unchecked")
    public void allRowsShouldBeUnchecked() {
        table = smartBearSoftwareWebOrdersPage.table;
        for(int i = 1; i < TableHandler.getRowCount(table); i++) {
            Assert.assertFalse(TableHandler.getCheckbox(table, i, 0).isSelected());
        }
    }

    @When("user clicks on {string} menu item")
    public void userClicksOnMenuItem(String menuItem) {
        smartBearSoftwareWebOrdersPage.selectItemByVisibleText(menuItem);
    }


    @And("user selects {string} as product")
    public void userSelectsAsProduct(String productName) {
        Select productDropdown = new Select(smartBearSoftwareWebOrdersPage.productDropdown);
        productDropdown.selectByVisibleText(productName);
    }


    @And("user enters {int} as quantity")
    public void userEntersAsQuantity(int productQuantity) {
        smartBearSoftwareWebOrdersPage.productQuantityInput.sendKeys(String.valueOf(productQuantity));
    }

    @And("user enters all address information")
    public void userEntersAllAddressInformation() {
        smartBearSoftwareWebOrdersPage.customerNameInput.sendKeys(CONSTANTS.CUSTOMER_NAME);
        smartBearSoftwareWebOrdersPage.customerStreetInput.sendKeys(CONSTANTS.ADDRESS_STREET);
        smartBearSoftwareWebOrdersPage.customerCityInput.sendKeys(CONSTANTS.ADDRESS_CITY);
        smartBearSoftwareWebOrdersPage.customerStateInput.sendKeys(CONSTANTS.ADDRESS_STATE);
        smartBearSoftwareWebOrdersPage.customerZipCodeInput.sendKeys(CONSTANTS.ADDRESS_ZIP);
    }

    @And("user enters all payment information")
    public void userEntersAllPaymentInformation() {
        table = smartBearSoftwareWebOrdersPage.customerCardType;
        TableHandler.selectRadioBtnByVisibleText(table, 0, "Visa");
        smartBearSoftwareWebOrdersPage.customerCardNumberInput.sendKeys(CONSTANTS.CARD_NUMBER);
        smartBearSoftwareWebOrdersPage.customerCardExpInput.sendKeys(CONSTANTS.CARD_EXPIRATION);
    }

    @Then("user should see their order displayed in the {string} table")
    public void userShouldSeeTheirOrderDisplayedInTheTable(String headerText) {
        table = smartBearSoftwareWebOrdersPage.table;

        Assert.assertTrue(smartBearSoftwareWebOrdersPage.subHeading.isDisplayed());
        Assert.assertEquals(headerText, smartBearSoftwareWebOrdersPage.subHeading.getText());
        Assert.assertEquals(CONSTANTS.CUSTOMER_NAME, TableHandler.getCell(table, 1, 1).getText());
    }

    @And("validate all information entered displayed correct with the order")
    public void validateAllInformationEnteredDisplayedCorrectWithTheOrder() {
        Assert.assertEquals(CONSTANTS.CUSTOMER_NAME, TableHandler.getCell(table, 1, 1).getText());

        Assert.assertEquals(CONSTANTS.PRODUCT_TYPE, TableHandler.getCell(table, 1, 2).getText());
        Assert.assertEquals(CONSTANTS.PRODUCT_QUANTITY, TableHandler.getCell(table, 1, 3).getText());

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String today = formatter.format(date);
        Assert.assertEquals(today, TableHandler.getCell(table, 1, 4).getText());

        Assert.assertEquals(CONSTANTS.ADDRESS_STREET, TableHandler.getCell(table, 1, 5).getText());
        Assert.assertEquals(CONSTANTS.ADDRESS_CITY, TableHandler.getCell(table, 1, 6).getText());
        Assert.assertEquals(CONSTANTS.ADDRESS_STATE, TableHandler.getCell(table, 1, 7).getText());
        Assert.assertEquals(CONSTANTS.ADDRESS_ZIP, TableHandler.getCell(table, 1, 8).getText());

        Assert.assertEquals(CONSTANTS.CARD_TYPE, TableHandler.getCell(table, 1, 9).getText());
        Assert.assertEquals(CONSTANTS.CARD_NUMBER, TableHandler.getCell(table, 1, 10).getText());
        Assert.assertEquals(CONSTANTS.CARD_EXPIRATION, TableHandler.getCell(table, 1, 11).getText());
    }

    @Then("validate all orders are deleted from the {string}")
    public void validateAllOrdersAreDeletedFromThe(String headerText) {
        Assert.assertTrue(smartBearSoftwareWebOrdersPage.subHeading.isDisplayed());
        Assert.assertEquals(headerText, smartBearSoftwareWebOrdersPage.subHeading.getText());
    }

    @And("validate user sees {string} message")
    public void validateUserSeesMessage(String message) {
        Assert.assertEquals(message, smartBearSoftwareWebOrdersPage.emptyOrderMessage.getText());
    }
}