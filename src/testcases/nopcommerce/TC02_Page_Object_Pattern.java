package testcases.nopcommerce;

import actions.pageObjects.users.UserHomePageObject;
import actions.pageObjects.users.UserRegisterPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TC02_Page_Object_Pattern {

    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");
    private String EmailAddress;
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;


    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        switch (browserName) {
            case "Firefox":
                System.setProperty("webdriver.gecko.driver", projectPath + File.separator + "browserDrivers" + File.separator + "geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", projectPath + File.separator + "browserDrivers" + File.separator + "chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "Edge":
                System.setProperty("webdriver.edge.driver", projectPath + File.separator + "browserDrivers" + File.separator + "msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("This Browser is not support");
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        EmailAddress = "afc" + generateFakeNumber();
        driver.get(url);
        // khoi tao driver Homepage
        homePage = new UserHomePageObject(driver);
    }

    @Test
    public void TC_01_Register_Empty_Data() {
        homePage.clickToRegisterLink();
        // khoi tao driver RegisterPage
        registerPage = new UserRegisterPageObject(driver);
        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
        Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
        Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");
    }

    @Test
    public void TC_02_Register_Invalid_Email() {
        homePage.clickToRegisterLink();
        // khoi tao driver RegisterPage
        registerPage = new UserRegisterPageObject(driver);

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
        homePage.clickToRegisterLink();
        // khoi tao driver RegisterPage
        registerPage = new UserRegisterPageObject(driver);

        registerPage.sendKeyToFirstNameTextBox("Automation");
        registerPage.sendKeyToLastNameTextBox("FC");
        registerPage.sendKeyToEmailTextBox(EmailAddress);
        System.out.println("Email: " + EmailAddress);
        registerPage.sendKeyToPasswordTextBox("123456");
        registerPage.sendKeyToConfirmPasswordTextBox("123456");

        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed");
        registerPage.clickToLogoutLink();
        // khoi tao driver HomePage
        homePage = new UserHomePageObject(driver);
    }

    @Test
    public void TC_04_Register_Existing_Email() {
        homePage.clickToRegisterLink();
        // khoi tao driver RegisterPage
        registerPage = new UserRegisterPageObject(driver);

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
        homePage.clickToRegisterLink();
        // khoi tao driver RegisterPage
        registerPage = new UserRegisterPageObject(driver);

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
        homePage.clickToRegisterLink();
        // khoi tao driver RegisterPage
        registerPage = new UserRegisterPageObject(driver);

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
        driver.quit();
    }

    public String generateFakeNumber() {
        Random random = new Random();
        return random.nextInt(999999) + "@gmail.com";
    }
}
