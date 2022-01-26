package actions.pageObjects.JQuery_Datatable;

import actions.commons.BasePage;
import interfaces.pageUIs.JQuery_Datatable.HomePageUI;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    //https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/

    public void openPagingByPageNumber(String pageNumber) {
        waitForElementClickable(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
        clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
    }


    public void enterToHeaderTextboxByLabel(String headerLabel, String searchValue) {
        waitForElementVisible(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL_NAME, headerLabel);
        sendKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL_NAME, searchValue, headerLabel);
        pressKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL_NAME, Keys.ENTER, headerLabel);
    }


    public boolean isPageNumberActived(String pageNumber) {
        waitForElementVisible(driver, HomePageUI.PAGINATION_PAGE_ACTIVED_BY_NUMBER, pageNumber);
        return isElementDisplayed(driver, HomePageUI.PAGINATION_PAGE_ACTIVED_BY_NUMBER, pageNumber);
    }

    public String getValueTextboxByLabel(String searchItemName, String searchValue) {
        waitForElementVisible(driver, HomePageUI.VALUE_TEXTBOX_BY_LABEL, searchItemName, searchValue);
        return getElementText(driver, HomePageUI.VALUE_TEXTBOX_BY_LABEL, searchItemName, searchValue);
    }

    public List<String> pagingAndGetAllDataEachRowAtAllPage() {
        int totalPage = getElementSizes(driver, HomePageUI.LIST_PAGE_NUMBER);
        System.out.println("Total size = " + totalPage);

        List<String> allRowValuesAllPage = new ArrayList<String>();

        for(int index = 1; index <= totalPage; index++ ) {
            waitForElementClickable(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, String.valueOf(index));
            clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, String.valueOf(index));
            isPageNumberActived(String.valueOf(index));

            List<WebElement> allRowEachPage = getListWebElements(driver, HomePageUI.ALL_ROW_EACH_PAGE);
            for(WebElement eachRow : allRowEachPage) {
                allRowValuesAllPage.add(eachRow.getText());
            }
        }

        for(String value : allRowValuesAllPage) {
            System.out.println("------------");
            System.out.println("show data :" + value);
        }

        return allRowValuesAllPage;
    }



    //https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/
    public void enterToTextboxByColumnNameAtRowNumber(String columnName, String rowNumber, String valueTextbox) {
        // lấy index của column name
        int columnNameindex = getElementSizes(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
        // sendkey vào rownumber
        waitForElementVisible(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnNameindex));
        sendKeyToElement(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, valueTextbox, rowNumber, String.valueOf(columnNameindex));
    }

    public void selectDropDowByColumNameAtRowNumber(String columnName, String rowNumber, String selectValue) {
        // lấy index của column name
        int columnNameindex = getElementSizes(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
        // select vào rownumber
        waitForElementClickable(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnNameindex));
        selectItemInDefaultDropDown(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, selectValue, rowNumber, String.valueOf(columnNameindex));
    }

    public void checkToCheckBoxByColumnNameAtRowNumber(String columnName, String rowNumber) {
        // lấy index của column name
        int columnNameindex = getElementSizes(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
        // select vào rownumber
        waitForElementClickable(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnNameindex));
        checkToRadioOrCheckbox(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnNameindex));
    }

    public void createFiveRowInput() {
        for(int numberOfClicks = 1; numberOfClicks < 5; numberOfClicks++) {
            waitForElementVisible(driver, HomePageUI.CREATE_ROW_BUTTON);
            clickToElement(driver, HomePageUI.CREATE_ROW_BUTTON);
        }
    }

    public void clickToUiButtonOfRowNumber(String rowNumber, String titleName) {
        waitForElementClickable(driver, HomePageUI.UI_BUTTON_NAME_OF_ROW_NUMBER, rowNumber, titleName);
        clickToElement(driver, HomePageUI.UI_BUTTON_NAME_OF_ROW_NUMBER, rowNumber, titleName);
    }
}
