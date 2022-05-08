package testcases.ManageDataTest;

import actions.commons.BaseTest;
import actions.commons.PageGenerationManager;
import actions.pageObjects.users.UserCustomerInfoPageObject;
import actions.pageObjects.users.UserHomePageObject;
import actions.pageObjects.users.UserLoginPageObject;
import actions.pageObjects.users.UserRegisterPageObject;
import actions.reportConfig.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import datatest.DataTestWithJson.Customer;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataTestWithJson extends BaseTest {

    private WebDriver driver;
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserLoginPageObject loginPage;
    private UserCustomerInfoPageObject customerInfoPage;
    private Customer customer;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        // Refactor Multiple Browser
        driver = getBrowserDriver(browserName, url);
        // khoi tao driver Homepage
        //homePage = new HomePageObject(driver);
        homePage = PageGenerationManager.getUserHomePage(driver);
        customer = Customer.getCustomer();
    }

    @Test
    public void TC_01_Register_Success(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_01_Register_Success");

        ExtentTestManager.getTest().log(LogStatus.INFO, "Register -  Step 01: Navigate To 'Register' page");
        registerPage = homePage.clickToRegisterLink();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Register -  Step 02: Enter To FirstNameTextBox with value is '" + customer.getFirstName() + "'" );
        registerPage.sendKeyToFirstNameTextBox(customer.getFirstName());

        ExtentTestManager.getTest().log(LogStatus.INFO, "Register -  Step 03: Enter To LastNameTextBox with value is '" + customer.getLastName() + "'" );
        registerPage.sendKeyToLastNameTextBox(customer.getLastName());

        ExtentTestManager.getTest().log(LogStatus.INFO, "Register -  Step 04: Enter To EmailTextBox with value is '" + customer.getEmailAddress() + "'" );
        registerPage.sendKeyToEmailTextBox(customer.getEmailAddress());

        ExtentTestManager.getTest().log(LogStatus.INFO, "Register -  Step 05: Enter To PasswordTextBox with value is '" + customer.getPassword() + "'" );
        registerPage.sendKeyToPasswordTextBox(customer.getPassword());

        ExtentTestManager.getTest().log(LogStatus.INFO, "Register -  Step 06: Enter To ConfirmPasswordTextBox with value is '" + customer.getConfirmPassword() + "'" );
        registerPage.sendKeyToConfirmPasswordTextBox(customer.getConfirmPassword());

        ExtentTestManager.getTest().log(LogStatus.INFO, "Register -  Step 07: Click To Register Button" );
        registerPage.clickToRegisterButton();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Register -  Step 08: Verify register success message is displayed" );
        Assert.assertEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed...");

        ExtentTestManager.endTest();
    }

    @Test
    public void TC_02_Login(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_02_Login");

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login -  Step 00: Click To logout Link" );
        homePage = registerPage.clickToLogoutLink();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login -  Step 01: Click To login Link" );
        loginPage =  homePage.clickToLoginLink();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login -  Step 02: Sendkey To EmailTextBox with value is '" + customer.getEmailAddress() + "'" );
        loginPage.sendKeyToEmailTextBox(customer.getEmailAddress());

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login -  Step 03: Sendkey To PasswordTextBox with value is '" + customer.getPassword() + "'" );
        loginPage.sendKeyToPasswordTextBox(customer.getPassword());

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login -  Step 04: Click To Login Button" );
        loginPage.clickLoginButton();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login -  Step 05: Click To Login Button" );
        customerInfoPage = homePage.clickToMyAccountLink();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login -  Step 06: Verify FirstNameTextBoxValue Equal with '" + customer.getFirstName() + "'");
        Assert.assertEquals(customerInfoPage.getFirstNameTextBoxValue(), customer.getFirstName());

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login -  Step 07: Verify LastNameTextBoxValue Equal with '" + customer.getLastName() + "'");
        Assert.assertEquals(customerInfoPage.getLastNameTextBoxValue(), customer.getLastName());

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login -  Step 08: Verify EmailTextBoxValue Equal with '" + customer.getEmailAddress() + "'");
        Assert.assertEquals(customerInfoPage.getEmailTextBoxValue(), customer.getEmailAddress());

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login -  Step 09: Co tinh sai");
        Assert.assertEquals(customerInfoPage.getEmailTextBoxValue(), "abc");

        ExtentTestManager.endTest();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }

}
