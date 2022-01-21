package actions.pageObjects.users;

import actions.commons.BasePage;
import actions.commons.PageGenerationManager;
import interfaces.pageUIs.users.UserRegisterPageUI;
import org.openqa.selenium.WebDriver;

public class UserRegisterPageObject extends BasePage{

    private WebDriver driver;

    public UserRegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToRegisterButton() {
        waitForElementVisible(driver, UserRegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
    }

    public String getFirstNameErrorMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
    }

    public String getLastNameErrorMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.LASTNAME_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.LASTNAME_ERROR_MESSAGE);
    }

    public String getEmailErrorMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
    }

    public String getPasswordErrorMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
    }

    public String getConfirmPasswordErrorMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
    }

    public void sendKeyToFirstNameTextBox(String value) {
        waitForElementVisible(driver, UserRegisterPageUI.FIRSTNAME_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.FIRSTNAME_TEXTBOX, value);
    }

    public void sendKeyToLastNameTextBox(String value) {
        waitForElementVisible(driver, UserRegisterPageUI.LASTNAME_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.LASTNAME_TEXTBOX, value);
    }

    public void sendKeyToEmailTextBox(String value) {
        waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, value);
    }

    public void sendKeyToPasswordTextBox(String value) {
        waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, value);
    }

    public void sendKeyToConfirmPasswordTextBox(String value) {
        waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, value);
    }

    public String getRegisteredSuccessMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    public UserHomePageObject clickToLogoutLink() {
        waitForElementVisible(driver, UserRegisterPageUI.LOGOUT_LINK);
        clickToElement(driver, UserRegisterPageUI.LOGOUT_LINK);
        return PageGenerationManager.getUserHomePage(driver);
    }

    public String getExistsEmailErrorMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.EXISTED_EMAIL_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.EXISTED_EMAIL_ERROR_MESSAGE);
    }
}
