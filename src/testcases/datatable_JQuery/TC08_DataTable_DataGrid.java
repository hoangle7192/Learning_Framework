package testcases.datatable_JQuery;

import actions.commons.BaseTest;
import actions.commons.PageGenerationManager;
import actions.pageObjects.JQuery.HomePageObject;
import actions.pageObjects.JQuery.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC08_DataTable_DataGrid extends BaseTest {

    WebDriver driver;
    HomePageObject homePage;

    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName, String appUrl) {
        driver = getBrowserDriver(browserName, appUrl);
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    //@Test
    public void Table_01_Paging() {
        homePage.openPagingByPageNumber("10");
        homePage.sleepInSecond(1);
        Assert.assertTrue(homePage.isPageNumberActived("10"));


        homePage.openPagingByPageNumber("9");
        homePage.sleepInSecond(1);
        Assert.assertTrue(homePage.isPageNumberActived("9"));

        homePage.openPagingByPageNumber("8");
        homePage.sleepInSecond(1);
        Assert.assertTrue(homePage.isPageNumberActived("8"));

        homePage.openPagingByPageNumber("3");
        homePage.sleepInSecond(1);
        Assert.assertTrue(homePage.isPageNumberActived("3"));
    }

    //@Test
    public void Table_02_Search() {
        homePage.refreshPage(driver);
        homePage.enterToHeaderTextboxByLabel("Females", "276880");

        homePage.sleepInSecond(3);

        Assert.assertEquals(homePage.getValueTextboxByLabel("females", "276880"), "276880");
        Assert.assertEquals(homePage.getValueTextboxByLabel("country", "Angola"), "Angola");
        Assert.assertEquals(homePage.getValueTextboxByLabel("males", "276472"), "276472");
        Assert.assertEquals(homePage.getValueTextboxByLabel("total", "553353"), "553353");
    }

    @Test
    public void Table_03_Get_All() {
        homePage.pagingAndGetAllDataEachRowAtAllPage();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        quitDriver();
    }
}
