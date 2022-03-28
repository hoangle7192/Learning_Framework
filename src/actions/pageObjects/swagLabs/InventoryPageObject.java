package actions.pageObjects.swagLabs;

import actions.commons.BasePage;
import interfaces.pageUIs.swagLabs.InventoryPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class InventoryPageObject extends BasePage {

    WebDriver driver;

    public InventoryPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void selectProductSortContainer(String selectValue) {
        waitForElementVisible(driver, InventoryPageUI.SELECT_PRODUCT_CONTAINER);
        selectItemInDefaultDropDown(driver, InventoryPageUI.SELECT_PRODUCT_CONTAINER, selectValue);
    }

    public boolean isProductNameSortASC() {
        List<WebElement> productNameElements = getListWebElements(driver, InventoryPageUI.PRODUCT_NAME_TEXT);
        ArrayList<String> productNameText = new ArrayList<>();

        for(WebElement productName : productNameElements) {
            productNameText.add(productName.getText());
        }

        ArrayList<String> productNameTextClone = new ArrayList<>(productNameText);
        Collections.sort(productNameTextClone);

        return productNameText.equals(productNameTextClone);
    }

    public boolean isProductNameSortDESC() {
        List<WebElement> productNameElements = getListWebElements(driver, InventoryPageUI.PRODUCT_NAME_TEXT);
        ArrayList<String> productNameText = new ArrayList<>();

        for(WebElement productName : productNameElements) {
            productNameText.add(productName.getText());
        }

        ArrayList<String> productNameTextClone = new ArrayList<>(productNameText);
        Collections.sort(productNameTextClone);
        Collections.reverse(productNameTextClone);

        return productNameText.equals(productNameTextClone);
    }

    public boolean isProductPriceSortASC() {
        List<WebElement> productPriceElements = getListWebElements(driver, InventoryPageUI.PRODUCT_PRICE_TEXT);
        ArrayList<Float> productPriceFloat = new ArrayList<>();

        for(WebElement element : productPriceElements) {
            Float productPrice = Float.valueOf(element.getText().replace("$", "").trim());
            productPriceFloat.add(productPrice);
        }

        ArrayList<Float> productPriceFloatClone = new ArrayList<>(productPriceFloat);
        Collections.sort(productPriceFloatClone);

        return productPriceFloat.equals(productPriceFloatClone);
    }

    public boolean isProductPriceSortDESC() {
        List<WebElement> productPriceElements = getListWebElements(driver, InventoryPageUI.PRODUCT_PRICE_TEXT);
        ArrayList<Float> productPriceFloat = new ArrayList<>();

        for(WebElement element : productPriceElements) {
            Float productPrice = Float.valueOf(element.getText().replace("$", "").trim());
            productPriceFloat.add(productPrice);
        }

        ArrayList<Float> productPriceFloatClone = new ArrayList<>(productPriceFloat);
        Collections.sort(productPriceFloatClone);
        Collections.reverse(productPriceFloatClone);

        return productPriceFloat.equals(productPriceFloatClone);
    }

    // Sort Date Time
    public boolean isDateTImeSortASC() {
        List<WebElement> dateTimeElements = getListWebElements(driver, InventoryPageUI.DATE_TIME);
        ArrayList<Date> dateTimeList = new ArrayList<>();

        for(WebElement element : dateTimeElements) {
            dateTimeList.add(convertStringToDate(element.getText()));
        }

        ArrayList<Date> dateTimeListClone = new ArrayList<>(dateTimeList);
        Collections.sort(dateTimeListClone);

        return dateTimeList.equals(dateTimeListClone);
    }

    public boolean isDateTImeSortDESC() {
        List<WebElement> dateTimeElements = getListWebElements(driver, InventoryPageUI.DATE_TIME);
        ArrayList<Date> dateTimeList = new ArrayList<>();

        for(WebElement element : dateTimeElements) {
            dateTimeList.add(convertStringToDate(element.getText()));
        }

        ArrayList<Date> dateTimeListClone = new ArrayList<>(dateTimeList);
        Collections.sort(dateTimeListClone);
        Collections.reverse(dateTimeListClone);

        return dateTimeList.equals(dateTimeListClone);
    }

    // Ham xu ly convert tu String thanh Date
    // http://truongleduan.quangtri.gov.vn/vi/news/Tin-hoat-dong-nha-truong/
    public Date convertStringToDate(String dateInString) {
        dateInString = dateInString.replace(",", ""); // Aug 5, 2019
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        Date date = null;
        try {
            date = formatter.parse(dateInString);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
