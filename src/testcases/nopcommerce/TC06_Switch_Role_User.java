package testcases.nopcommerce;

import actions.commons.BaseTest;
import actions.commons.GlobalConstants;
import actions.commons.PageGenerationManager;
import actions.pageObjects.admin.AdminDashboardPageObject;
import actions.pageObjects.admin.AdminLoginPageObject;
import actions.pageObjects.users.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC06_Switch_Role_User extends BaseTest {

    private WebDriver driver;
    private UserHomePageObject userHomePage;
    private UserRegisterPageObject userRegisterPage;
    private UserLoginPageObject userLoginPage;
    private UserCustomerInfoPageObject userCustomerInfoPage;

    private AdminLoginPageObject adminLoginPage;
    private AdminDashboardPageObject adminDashBoardPage;

    private String firstName, lastName, emailAddress, password, confirmPassword;
    private String adminEmail, adminPassword;


    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {
        // 1. Quyen User -> Mo trang Homepage
        driver = getBrowserDriver(browserName, GlobalConstants.USER_URL);
        userHomePage = PageGenerationManager.getUserHomePage(driver);

        firstName = "Automation";
        lastName = "FC";
        password = "123456";
        confirmPassword = "123456";
        emailAddress = "afc" + generateFakeNumber();
        adminEmail = "admin@yourstore.com";
        adminPassword = "admin";
    }


    @Test
    public void TC_01_Created_Account() {
        userRegisterPage = userHomePage.clickToRegisterLink();
        userRegisterPage.sendKeyToFirstNameTextBox(firstName);
        userRegisterPage.sendKeyToLastNameTextBox(lastName);
        userRegisterPage.sendKeyToEmailTextBox(emailAddress);
        userRegisterPage.sendKeyToPasswordTextBox(password);
        userRegisterPage.sendKeyToConfirmPasswordTextBox(confirmPassword);
        userRegisterPage.clickToRegisterButton();
        userHomePage = userRegisterPage.clickToLogoutLink();
    }

    @Test
    public void TC_02_Switch_User_To_Admin() {
        // Homepage -> Login (User)
        userLoginPage = userHomePage.clickToLoginLink();
        userHomePage = userLoginPage.loginAsUser(emailAddress, password);

        // Logout ra -> Homepage
        userHomePage.clickToLogoutLink();

        // Homepage(user) -> Open Admin URL -> Login (Admin) -> Dashboard
        adminLoginPage = userHomePage.openAdminURL(GlobalConstants.ADMIN_URL);
        adminDashBoardPage = adminLoginPage.loginAsAdmin(adminEmail, adminPassword);

        // Dashboard -> Any Page -> Logout -> Login (Admin)
        adminLoginPage = adminDashBoardPage.clickToAdminLogoutLink(driver);
    }

    @Test
    public void TC_03_Switch_Admin_To_User() {
        userHomePage = adminLoginPage.openUrlUser(GlobalConstants.USER_URL);
        userLoginPage = userHomePage.clickToLoginLink();
        userHomePage = userLoginPage.loginAsUser(emailAddress, password);
        userCustomerInfoPage = userHomePage.clickToMyAccountLink();
        userHomePage = userCustomerInfoPage.clickToUserLogoutLink(driver);
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        quitDriver();
    }

}
