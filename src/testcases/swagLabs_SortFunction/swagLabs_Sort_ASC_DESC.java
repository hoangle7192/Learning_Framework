package testcases.swagLabs_SortFunction;

import actions.commons.BaseTest;
import actions.commons.PageGenerationManager;
import actions.pageObjects.swagLabs.InventoryPageObject;
import actions.pageObjects.swagLabs.LoginPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class swagLabs_Sort_ASC_DESC extends BaseTest {
    WebDriver driver;

    LoginPageObject loginPage;
    InventoryPageObject inventoryPage;

    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        loginPage = PageGenerationManager.getLoginPage(driver);
        loginPage.enterToUserNameTextBox("standard_user");
        loginPage.enterToPasswordTextBox("secret_sauce");
        inventoryPage = loginPage.clickToLoginButton();
    }

    @Test
    public void Sort_Name_ASC() {
        inventoryPage.selectProductSortContainer("Name (A to Z)");
        Assert.assertTrue(inventoryPage.isProductNameSortASC());
    }

    @Test
    public void Sort_Name_ASC_FAULT() {
        inventoryPage.selectProductSortContainer("Name (A to Z)");
        Assert.assertTrue(inventoryPage.isProductNameSortDESC());
    }

    @Test
    public void Sort_Name_DESC() {
        inventoryPage.selectProductSortContainer("Name (Z to A)");
        Assert.assertTrue(inventoryPage.isProductNameSortDESC());
    }

    @Test
    public void Sort_Price_ASC() {
        inventoryPage.selectProductSortContainer("Price (low to high)");
        Assert.assertTrue(inventoryPage.isProductPriceSortASC());
    }

    @Test
    public void Sort_Price_DESC() {
        inventoryPage.selectProductSortContainer("Price (high to low)");
        Assert.assertTrue(inventoryPage.isProductPriceSortDESC());
    }


    // Sort Date time
    @Test
    public void Sort_DateTime_ASC() {
        Assert.assertTrue(inventoryPage.isDateTImeSortASC());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
