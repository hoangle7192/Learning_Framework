package actions.pageObjects.swagLabs;

import actions.commons.BasePage;
import actions.commons.PageGenerationManager;
import interfaces.pageUIs.swagLabs.LoginPageUI;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends BasePage {

    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToUserNameTextBox(String userNameValue) {
        waitForElementVisible(driver, LoginPageUI.USERNAME_TEXT_BOX);
        sendKeyToElement(driver, LoginPageUI.USERNAME_TEXT_BOX, userNameValue);
    }

    public void enterToPasswordTextBox(String passwordValue) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXT_BOX);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXT_BOX, passwordValue);
    }

    public InventoryPageObject clickToLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGenerationManager.getInventoryPage(driver);
    }
}
