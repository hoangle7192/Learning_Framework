package actions.pageObjects.users;

import actions.commons.BasePage;
import actions.commons.PageGenerationManager;
import interfaces.pageUIs.users.UserLoginPageUI;
import org.openqa.selenium.WebDriver;

public class UserLoginPageObject extends BasePage{

    WebDriver driver;

    public UserLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void sendKeyToEmailTextBox(String emailAddress) {
        waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void sendKeyToPasswordTextBox(String password) {
        waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public UserHomePageObject clickLoginButton() {
        waitForElementVisible(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
        return PageGenerationManager.getUserHomePage(driver);
    }

    public UserHomePageObject loginAsUser(String emailAddress, String password) {
        sendKeyToEmailTextBox(emailAddress);
        sendKeyToPasswordTextBox(password);
        return clickLoginButton();
    }
}




