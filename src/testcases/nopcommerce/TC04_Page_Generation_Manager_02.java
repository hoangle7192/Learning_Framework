package testcases.nopcommerce;

import actions.commons.BaseTest;
import actions.pageObjects.users.UserHomePageObject;
import actions.commons.PageGenerationManager;
import actions.pageObjects.users.UserRegisterPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC04_Page_Generation_Manager_02 extends BaseTest {

    private WebDriver driver;
    private String EmailAddress;
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;

    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        // Refactor Multiple Browser
        driver = getBrowserDriver(browserName, url);
        // khoi tao driver Homepage
        //homePage = new HomePageObject(driver);
        homePage = PageGenerationManager.getUserHomePage(driver);

        EmailAddress = "afc" + generateFakeNumber();
    }

    @Test
    public void TC_01_Register_Empty_Data() {
        registerPage = homePage.clickToRegisterLink();
        // khoi tao driver RegisterPage
        //registerPage = new RegisterPageObject(driver);
        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
        Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
        Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");
    }

    @Test
    public void TC_02_Register_Invalid_Email() {
        registerPage = homePage.clickToRegisterLink();
        // khoi tao driver RegisterPage
        //registerPage = new RegisterPageObject(driver);

        registerPage.sendKeyToFirstNameTextBox("Automation");
        registerPage.sendKeyToLastNameTextBox("FC");
        registerPage.sendKeyToEmailTextBox("123@123##");
        registerPage.sendKeyToPasswordTextBox("123456");
        registerPage.sendKeyToConfirmPasswordTextBox("123456");

        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "Wrong email");
    }

    @Test
    public void TC_03_Register_Success() {
        registerPage = homePage.clickToRegisterLink();
        // khoi tao driver RegisterPage
        //registerPage = new RegisterPageObject(driver);

        registerPage.sendKeyToFirstNameTextBox("Automation");
        registerPage.sendKeyToLastNameTextBox("FC");
        registerPage.sendKeyToEmailTextBox(EmailAddress);
        System.out.println("Email: " + EmailAddress);
        registerPage.sendKeyToPasswordTextBox("123456");
        registerPage.sendKeyToConfirmPasswordTextBox("123456");

        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed");
        homePage = registerPage.clickToLogoutLink();
        // khoi tao driver HomePage
        //homePage = new HomePageObject(driver);
    }

    @Test
    public void TC_04_Register_Existing_Email() {
        registerPage = homePage.clickToRegisterLink();
        // khoi tao driver RegisterPage
        //registerPage = new RegisterPageObject(driver);

        registerPage.sendKeyToFirstNameTextBox("Automation");
        registerPage.sendKeyToLastNameTextBox("FC");
        registerPage.sendKeyToEmailTextBox(EmailAddress);
        System.out.println("Email: " + EmailAddress);
        registerPage.sendKeyToPasswordTextBox("123456");
        registerPage.sendKeyToConfirmPasswordTextBox("123456");

        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getExistsEmailErrorMessage(), "The specified email already exists");
    }

    @Test
    public void TC_05_Register_Password_Less_Than_6_Chars() {
        registerPage = homePage.clickToRegisterLink();
        // khoi tao driver RegisterPage
        //registerPage = new RegisterPageObject(driver);

        registerPage.sendKeyToFirstNameTextBox("Automation");
        registerPage.sendKeyToLastNameTextBox("FC");
        registerPage.sendKeyToEmailTextBox(EmailAddress);
        System.out.println("Email: " + EmailAddress);
        registerPage.sendKeyToPasswordTextBox("123");
        registerPage.sendKeyToConfirmPasswordTextBox("123");

        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password must meet the following rules:\n" +
                "must have at least 6 characters");
    }

    @Test
    public void TC_06_Register_Invalid_Confirm_Password() {
        registerPage = homePage.clickToRegisterLink();
        // khoi tao driver RegisterPage
        //registerPage = new RegisterPageObject(driver);

        registerPage.sendKeyToFirstNameTextBox("Automation");
        registerPage.sendKeyToLastNameTextBox("FC");
        registerPage.sendKeyToEmailTextBox(EmailAddress);
        System.out.println("Email: " + EmailAddress);
        registerPage.sendKeyToPasswordTextBox("123");
        registerPage.sendKeyToConfirmPasswordTextBox("123123");

        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "The password and confirmation password do not match.");
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        quitDriver();
    }

}
