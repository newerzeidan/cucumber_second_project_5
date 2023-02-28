package utilities;

import co.boorse.seleniumtable.SeleniumTable;
import co.boorse.seleniumtable.SeleniumTableCell;
import co.boorse.seleniumtable.SeleniumTableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TableHandler {
    public static List<SeleniumTableRow> getAllRows(WebElement tableElement) {
        SeleniumTable seleniumTable = SeleniumTable.getInstance(tableElement);
        return seleniumTable.rows();
    }

    public static SeleniumTableRow getRow(WebElement tableElement, int rowIndex) {
        return getAllRows(tableElement).get(rowIndex);
    }

    public static List<SeleniumTableCell> getCells(WebElement tableElement, int rowIndex) {
        return getRow(tableElement, rowIndex).cells();
    }

    public static SeleniumTableCell getCell(WebElement tableElement, int rowIndex, int cellIndex) {
        return getRow(tableElement, rowIndex).get(cellIndex);
    }

    public static WebElement getCheckbox(WebElement tableElement, int rowIndex, int cellIndex) {
        return getCell(tableElement, rowIndex, cellIndex).getElement().findElement(By.tagName("input"));
    }

    public static void selectRadioBtnByVisibleText(WebElement tableElement, int rowIndex, String labelName) {
        for(SeleniumTableCell cell : getCells(tableElement, rowIndex)) {
            WebElement label = cell.getElement().findElement(By.tagName("label"));
            if(label.getText().equals(labelName)) {
                label.click();
                break;
            }
        }
    }
    public static int getRowCount(WebElement tableElement) {
        return getAllRows(tableElement).size();
    }
}