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

public class TC07_Dynamic_Locator extends BaseTest {

    private WebDriver driver;
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserLoginPageObject loginPage;
    private UserCustomerInfoPageObject customerInfoPage;
    private UserOrdersPageObject orderPage;
    private UserRewardPointPageObject rewardPointPage;
    private UserAddressPageObject addressPage;
    private UserDownloadableProductsPageObject downloadableProductsPage;
    private UserBackInStockSubscriptionsPageObject backInStockSubscriptionsPage;
    private UserChangePasswordPageObject changePasswordPage;
    private UserMyProductReviewsPageObject myProductReviewsPage;
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
        registerPage = homePage.clickToRegisterLink();
        // khoi tao driver RegisterPage
        //registerPage = new RegisterPageObject(driver);

        registerPage.sendKeyToFirstNameTextBox(firstName);
        registerPage.sendKeyToLastNameTextBox(lastName);
        registerPage.sendKeyToEmailTextBox(emailAddress);
        System.out.println("Email: " + emailAddress);
        registerPage.sendKeyToPasswordTextBox(password);
        registerPage.sendKeyToConfirmPasswordTextBox(confirmPassword);

        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed");
        homePage = registerPage.clickToLogoutLink();

        loginPage =  homePage.clickToLoginLink();
        loginPage.sendKeyToEmailTextBox(emailAddress);
        loginPage.sendKeyToPasswordTextBox(password);
        loginPage.clickLoginButton();


        customerInfoPage = homePage.clickToMyAccountLink();
        Assert.assertEquals(customerInfoPage.getFirstNameTextBoxValue(), firstName);
        Assert.assertEquals(customerInfoPage.getLastNameTextBoxValue(), lastName);
        Assert.assertEquals(customerInfoPage.getEmailTextBoxValue(), emailAddress);
    }


    @Test
    public void TC_02_Dynamic_Page_Cach_01() {
        addressPage = (UserAddressPageObject) customerInfoPage.openDynamicPagesAtMyAccountByNameItPage(driver, "Addresses");
        orderPage = (UserOrdersPageObject) addressPage.openDynamicPagesAtMyAccountByNameItPage(driver, "Orders");
        downloadableProductsPage = (UserDownloadableProductsPageObject) orderPage.openDynamicPagesAtMyAccountByNameItPage(driver, "Downloadable products");
        backInStockSubscriptionsPage = (UserBackInStockSubscriptionsPageObject) downloadableProductsPage.openDynamicPagesAtMyAccountByNameItPage(driver, "Back in stock subscriptions");
        rewardPointPage = (UserRewardPointPageObject) backInStockSubscriptionsPage.openDynamicPagesAtMyAccountByNameItPage(driver, "Reward points");
        changePasswordPage = (UserChangePasswordPageObject) rewardPointPage.openDynamicPagesAtMyAccountByNameItPage(driver, "Change password");
        myProductReviewsPage = (UserMyProductReviewsPageObject) changePasswordPage.openDynamicPagesAtMyAccountByNameItPage(driver, "My product reviews");
        customerInfoPage = (UserCustomerInfoPageObject) myProductReviewsPage.openDynamicPagesAtMyAccountByNameItPage(driver, "Customer info");
    }

    @Test
    public void TC_03_Dynamic_Page_Cach_02() {
        customerInfoPage.openDynamicPagesAtMyAccountByNameNhieuPage(driver, "Addresses");
        addressPage = PageGenerationManager.getUserAddressesPage(driver);

        addressPage.openDynamicPagesAtMyAccountByNameNhieuPage(driver, "My product reviews");
        myProductReviewsPage= PageGenerationManager.getUserMyProductReviews(driver);

        myProductReviewsPage.openDynamicPagesAtMyAccountByNameNhieuPage(driver, "Reward points");
        rewardPointPage= PageGenerationManager.getUserRewardPointPage(driver);

        rewardPointPage.openDynamicPagesAtMyAccountByNameNhieuPage(driver, "Orders");
        orderPage= PageGenerationManager.getUserOrderPage(driver);

        rewardPointPage.openDynamicPagesAtMyAccountByNameNhieuPage(driver, "Downloadable products");
        downloadableProductsPage= PageGenerationManager.getUserDownloadableProductsPage(driver);

        rewardPointPage.openDynamicPagesAtMyAccountByNameNhieuPage(driver, "Back in stock subscriptions");
        backInStockSubscriptionsPage= PageGenerationManager.getUserBackInStockSubscriptionsPage(driver);

        rewardPointPage.openDynamicPagesAtMyAccountByNameNhieuPage(driver, "Change password");
        changePasswordPage= PageGenerationManager.getUserChangePasswordPage(driver);
    }

    //    @Test
//    public void TC_02_Switch_Page() {
//        addressPage = customerInfoPage.openAddressesPage(driver);
//        orderPage = addressPage.openOrdersPage(driver);
//        downloadableProductsPage = orderPage.openDownloadableProductsPage(driver);
//        backInStockSubscriptionsPage = downloadableProductsPage.openBackInStockSubscriptionsPage(driver);
//        rewardPointPage = backInStockSubscriptionsPage.openRewardPointPage(driver);
//        changePasswordPage = rewardPointPage.openChangePasswordPage(driver);
//        myProductReviewsPage = changePasswordPage.openMyProductReviewsPage(driver);
//        customerInfoPage = myProductReviewsPage.openCustomerInfoPage(driver);
//    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        quitDriver();
    }

}
