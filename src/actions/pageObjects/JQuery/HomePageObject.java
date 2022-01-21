package actions.pageObjects.JQuery;

import actions.commons.BasePage;
import interfaces.pageUIs.JQuery.HomePageUI;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

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

}
