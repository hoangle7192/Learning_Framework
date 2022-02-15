package testcases.nopcommerce;

import actions.commons.BaseTest;
import actions.commons.PageGenerationManager;
import actions.pageObjects.users.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC12_Log_ReportNG extends BaseTest {

    private WebDriver driver;
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserLoginPageObject loginPage;
    private UserCustomerInfoPageObject customerInfoPage;
    private String firstName, lastName, emailAddress, password, confirmPassword;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        // Refactor Multiple Browser
        driver = getBrowserDriver(browserName, url);
        // khoi tao driver Homepage
        //homePage = new HomePageObject(driver);
        homePage = PageGenerationManager.getUserHomePage(driver);

        firstName = "Automation";
        lastName = "FC";
        password = "123456";
        confirmPassword = "123456";
        emailAddress = "afc" + generateFakeNumber();
    }

    @Test
    public void TC_01_Register_Success() {
        log.info("Register -  Step 01: Navigate To 'Register' page");
        registerPage = homePage.clickToRegisterLink();

        log.info("Register -  Step 02: Enter To FirstNameTextBox with value is '" + firstName + "'" );
        registerPage.sendKeyToFirstNameTextBox(firstName);

        log.info("Register -  Step 03: Enter To LastNameTextBox with value is '" + lastName + "'" );
        registerPage.sendKeyToLastNameTextBox(lastName);

        log.info("Register -  Step 04: Enter To EmailTextBox with value is '" + emailAddress + "'" );
        registerPage.sendKeyToEmailTextBox(emailAddress);

        log.info("Register -  Step 05: Enter To PasswordTextBox with value is '" + password + "'" );
        registerPage.sendKeyToPasswordTextBox(password);

        log.info("Register -  Step 06: Enter To ConfirmPasswordTextBox with value is '" + confirmPassword + "'" );
        registerPage.sendKeyToConfirmPasswordTextBox(confirmPassword);

        log.info("Register -  Step 07: Click To Register Button" );
        registerPage.clickToRegisterButton();

        log.info("Register -  Step 08: Verify register success message is displayed" );
        Assert.assertEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed");

        log.info("Register -  Step 09: Click To logout Link" );
        homePage = registerPage.clickToLogoutLink();
    }

    @Test
    public void TC_02_Login() {
        log.info("Login -  Step 01: Click To login Link" );
        loginPage =  homePage.clickToLoginLink();

        log.info("Login -  Step 02: Sendkey To EmailTextBox with value is '" + emailAddress + "'" );
        loginPage.sendKeyToEmailTextBox(emailAddress);

        log.info("Login -  Step 03: Sendkey To PasswordTextBox with value is '" + password + "'" );
        loginPage.sendKeyToPasswordTextBox(password);

        log.info("Login -  Step 04: Click To Login Button" );
        loginPage.clickLoginButton();

        log.info("Login -  Step 05: Click To Login Button" );
        customerInfoPage = homePage.clickToMyAccountLink();

        log.info("Login -  Step 06: Verify FirstNameTextBoxValue Equal with '" + firstName + "'");
        Assert.assertEquals(customerInfoPage.getFirstNameTextBoxValue(), firstName);

        log.info("Login -  Step 07: Verify LastNameTextBoxValue Equal with '" + lastName + "'");
        Assert.assertEquals(customerInfoPage.getLastNameTextBoxValue(), lastName);

        log.info("Login -  Step 08: Verify EmailTextBoxValue Equal with '" + emailAddress + "'");
        Assert.assertEquals(customerInfoPage.getEmailTextBoxValue(), emailAddress);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        quitDriver();
    }

}
