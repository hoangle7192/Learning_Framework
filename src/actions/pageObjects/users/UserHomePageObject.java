package actions.pageObjects.users;

import actions.commons.BasePage;
import actions.commons.PageGenerationManager;
import actions.pageObjects.admin.AdminLoginPageObject;
import interfaces.pageUIs.users.UserHomePageUI;
import org.openqa.selenium.WebDriver;

public class UserHomePageObject extends BasePage {

    private WebDriver driver;

    public UserHomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public UserRegisterPageObject clickToRegisterLink() {
        waitForElementVisible(driver, UserHomePageUI.REGISTER_LINK);
        clickToElement(driver, UserHomePageUI.REGISTER_LINK);
        return PageGenerationManager.getUserRegisterPage(driver);
    }

    public UserLoginPageObject clickToLoginLink() {
        waitForElementVisible(driver, UserHomePageUI.LOGIN_LINK);
        clickToElement(driver, UserHomePageUI.LOGIN_LINK);
        return PageGenerationManager.getUserLoginPage(driver);
    }

    public UserCustomerInfoPageObject clickToMyAccountLink() {
        waitForElementVisible(driver, UserHomePageUI.MYACCOUNT_LINK);
        clickToElement(driver, UserHomePageUI.MYACCOUNT_LINK);
        return PageGenerationManager.getUserCustomerInfoPage(driver);
    }

    public void clickToLogoutLink() {
        waitForElementVisible(driver, UserHomePageUI.LOGOUT_LINK);
        clickToElement(driver, UserHomePageUI.LOGOUT_LINK);
    }


    public AdminLoginPageObject openAdminURL(String url) {
        openURL(driver, url);
        return PageGenerationManager.getAdminLoginPage(driver);
    }
}
