package testcases.nopcommerce;

import actions.commons.BaseTest;
import actions.commons.PageGenerationManager;
import actions.pageObjects.admin.AdminLoginPageObject;
import actions.pageObjects.users.UserHomePageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC06_Switch_Role_Admin extends BaseTest {

    private WebDriver driver;
    private UserHomePageObject userHomePage;
    private AdminLoginPageObject adminLoginPage;


    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        // 1. Quyen User -> Mo trang Homepage
        driver = getBrowserDriver(browserName, url);
        userHomePage = PageGenerationManager.getUserHomePage(driver);

        // 1. Quyen Admin -> Mo trang Login
        driver = getBrowserDriver(browserName, url);
        adminLoginPage = PageGenerationManager.getAdminLoginPage(driver);

    }

    @Test
    public void TC_01_Switch_User_To_Admin() {

    }

    @Test
    public void TC_01_Switch_Admin_To_User() {

    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        quitDriver();
    }

}
