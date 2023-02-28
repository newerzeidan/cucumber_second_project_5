package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SmartBearSoftwareWebOrdersPage extends SmartBearSoftwareLoginPage {
    public SmartBearSoftwareWebOrdersPage() {
        super();
    }

    @FindBy(css = "ul[id=ctl00_menu]>li")
    public List<WebElement> menuItemsList;

    @FindBy(id = "ctl00_MainContent_btnCheckAll")
    public WebElement btnCheckAll;

    @FindBy(id = "ctl00_MainContent_btnUncheckAll")
    public WebElement btnUncheckAll;

    @FindBy(id = "ctl00_MainContent_btnDelete")
    public WebElement btnDeleteSelected;

    @FindBy(id = "ctl00_MainContent_orderMessage")
    public WebElement emptyOrderMessage;

    @FindBy(css = "table[class=SampleTable]")
    public WebElement table;

    @FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
    public WebElement productDropdown;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
    public WebElement productQuantityInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtName")
    public WebElement customerNameInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox2")
    public WebElement customerStreetInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox3")
    public WebElement customerCityInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox4")
    public WebElement customerStateInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox5")
    public WebElement customerZipCodeInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_cardList")
    public WebElement customerCardType;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
    public WebElement customerCardNumberInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
    public WebElement customerCardExpInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
    public WebElement btnSubmitOrder;

    @FindBy(tagName = "h2")
    public WebElement subHeading;

    public void selectBtnByVisibleText(String btnName) {
        switch (btnName) {
            case "Check All":
                btnCheckAll.click();
                break;
            case "Uncheck All":
                btnUncheckAll.click();
                break;
            case "Delete Selected":
                btnDeleteSelected.click();
                break;
            case "Process":
                btnSubmitOrder.click();
                break;
        }
    }

    public void selectItemByVisibleText(String menuItem) {
        for(WebElement item : menuItemsList) {
            if(item.getText().equals(menuItem)) {
                item.click();
                break;
            }
        }
    }
}