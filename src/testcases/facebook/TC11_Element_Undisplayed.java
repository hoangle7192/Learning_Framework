package testcases.facebook;

import actions.commons.BaseTest;
import actions.commons.PageGenerationManager;
import actions.pageObjects.facebook.FacebookLoginPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC11_Element_Undisplayed extends BaseTest {
    private WebDriver driver;
    private FacebookLoginPageObject facebookLoginPage;

    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        facebookLoginPage = PageGenerationManager.getFacebookLoginPage(driver);
    }

    @Test
    public void TC_01_Verify_Element_Display() {
        facebookLoginPage.clickToCreateNewAccountButton();
        verifyTrue(facebookLoginPage.isEmailTextBoxDisplayed());
    }

    @Test
    public void TC_02_Verify_Element_UnDisplay_In_Dom() {
        facebookLoginPage.refreshPage(driver);

        // Verify Re_Enter_Email textbox displayed
        facebookLoginPage.clickToCreateNewAccountButton();
        facebookLoginPage.enterToEmailTextBox("a@gmail.com");
        verifyTrue(facebookLoginPage.isReEnterEmailTextBoxDisplayed());

        // Verify Re_Enter_Email textbox Undisplayed
        facebookLoginPage.refreshPage(driver);
        facebookLoginPage.clickToCreateNewAccountButton();
        //facebookLoginPage.enterToEmailTextBox("");
        verifyTrue(facebookLoginPage.isReEnterEmailTextBoxUnDisplayed());
    }

    @Test
    public void TC_03_Verify_Element_UnDisplay_Not_In_Dom() {
        facebookLoginPage.refreshPage(driver);
        verifyTrue(facebookLoginPage.isReEnterEmailTextBoxUnDisplayed());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        quitDriver();
    }
}
