package actions.pageObjects.admin;

import actions.commons.BasePage;
import actions.commons.PageGenerationManager;
import actions.pageObjects.users.UserHomePageObject;
import interfaces.pageUIs.admin.AdminLoginPageUI;
import org.openqa.selenium.WebDriver;

public class AdminLoginPageObject extends BasePage {
    WebDriver driver;

    public AdminLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void sendKeyToEmailTextbox(String adminEmail) {
        waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, adminEmail);
    }

    public void sendKeyToPasswordTextbox(String adminPassword) {
        waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, adminPassword);
    }

    public AdminDashboardPageObject clickLoginButton() {
        waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
        return PageGenerationManager.getAdminDashBoardPage(driver);
    }

    public AdminDashboardPageObject loginAsAdmin(String adminEmail, String adminPassword) {
        sendKeyToEmailTextbox(adminEmail);
        sendKeyToPasswordTextbox(adminPassword);
        return clickLoginButton();
    }

    public UserHomePageObject openUrlUser(String urlUser) {
        openURL(driver, urlUser);
        return PageGenerationManager.getUserHomePage(driver);
    }
}
