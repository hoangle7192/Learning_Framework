package actions.pageObjects.users;

import actions.commons.BasePage;
import interfaces.pageUIs.users.UserCustomerInfoPageUI;
import org.openqa.selenium.WebDriver;

public class UserCustomerInfoPageObject extends BasePage {

    WebDriver driver;

    public UserCustomerInfoPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstNameTextBoxValue() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.FIRSTNAME_TEXTBOX);
        return getElementAttributeValue(driver, UserCustomerInfoPageUI.FIRSTNAME_TEXTBOX, "value");
    }

    public String getLastNameTextBoxValue() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.LASTNAME_TEXTBOX);
        return getElementAttributeValue(driver, UserCustomerInfoPageUI.LASTNAME_TEXTBOX, "value");
    }
    public String getEmailTextBoxValue() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.EMAIL_TEXTBOX);
        return getElementAttributeValue(driver, UserCustomerInfoPageUI.EMAIL_TEXTBOX, "value");
    }


}
