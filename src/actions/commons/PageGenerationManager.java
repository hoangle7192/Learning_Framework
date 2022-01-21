package actions.commons;

import actions.pageObjects.admin.AdminDashboardPageObject;
import actions.pageObjects.admin.AdminLoginPageObject;
import actions.pageObjects.users.*;
import org.openqa.selenium.WebDriver;

public class PageGenerationManager {

    public static UserHomePageObject getUserHomePage(WebDriver driver) {
        return new UserHomePageObject(driver);
    }

    public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
        return new UserRegisterPageObject(driver);
    }

    public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
        return new UserLoginPageObject(driver);
    }

    public static UserCustomerInfoPageObject getUserCustomerInfoPage(WebDriver driver) {
        return new UserCustomerInfoPageObject(driver);
    }

    public static UserAddressPageObject getUserAddressesPage(WebDriver driver) {
        return new UserAddressPageObject(driver);
    }

    public static UserOrdersPageObject getUserOrderPage(WebDriver driver) {
        return new UserOrdersPageObject(driver);
    }

    public static UserDownloadableProductsPageObject getUserDownloadableProductsPage(WebDriver driver) {
        return new UserDownloadableProductsPageObject(driver);
    }

    public static UserBackInStockSubscriptionsPageObject getUserBackInStockSubscriptionsPage(WebDriver driver) {
        return new UserBackInStockSubscriptionsPageObject(driver);
    }

    public static UserRewardPointPageObject getUserRewardPointPage(WebDriver driver) {
        return new UserRewardPointPageObject(driver);
    }

    public static UserChangePasswordPageObject getUserChangePasswordPage(WebDriver driver) {
        return new UserChangePasswordPageObject(driver);
    }

    public static UserMyProductReviewsPageObject getUserMyProductReviews(WebDriver driver) {
        return new UserMyProductReviewsPageObject(driver);
    }

    public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
        return new AdminLoginPageObject(driver);
    }

    public static AdminDashboardPageObject getAdminDashBoardPage(WebDriver driver) {
        return new AdminDashboardPageObject(driver);
    }
}
