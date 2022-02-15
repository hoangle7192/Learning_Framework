package testcases.JQuery;

import actions.commons.BaseTest;
import actions.pageObjects.JQuery_Upload_Files.HomePageObject;
import actions.pageObjects.JQuery_Upload_Files.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC09_Upload_Files extends BaseTest {
    /*note:
    1. Tìm thẻ //input[@name='files[]'] hoac //input[@type='file']
    2. Thẻ input có thể visible/invisible => không care
    3. Chỉ cần sendkey là được
    4. Không được sendkey vào thẻ khác ngoài input
    * */

    private HomePageObject homePage;
    WebDriver driver;

    String file1 = "name01.jpg";
    String file2 = "name02.jpg";
    String file3 = "name03.jpg";
    String[] multipleFiles = {file1, file2, file3};

    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void Upload_01_One_File_Per_Time() {
        // Step01: load single file
        homePage.uploadMultipleFiles(driver, file1);

        // Step02: verify single file load successed
        Assert.assertTrue(homePage.isFileLoadedByName(file1));

        // Step03: click start button để upload file lên sever
        homePage.clickToStartButton();

        // Step04: verìy single file link upload successed
        Assert.assertTrue(homePage.isFileLinkUploadedByName(file1));

        // Step05: verìy single file image upload successed
        Assert.assertTrue(homePage.isFileImageUploadedByName(file1));

    }

   @Test
    public void Upload_02_Multiple_File_Per_Time() {
        homePage.refreshPage(driver);
        // Step01: load multiple file
        homePage.uploadMultipleFiles(driver, multipleFiles);

        // Step02: verify single file load successed
        Assert.assertTrue(homePage.isFileLoadedByName(file1));
        Assert.assertTrue(homePage.isFileLoadedByName(file2));
        Assert.assertTrue(homePage.isFileLoadedByName(file2));

        // Step03: click start button để upload file lên sever
        homePage.clickToStartButton();

        // Step04: verìy single file link upload successed
        Assert.assertTrue(homePage.isFileLinkUploadedByName(file1));
        Assert.assertTrue(homePage.isFileLinkUploadedByName(file2));
        Assert.assertTrue(homePage.isFileLinkUploadedByName(file2));

        // Step05: verìy single file image upload successed
        Assert.assertTrue(homePage.isFileImageUploadedByName(file1));
        Assert.assertTrue(homePage.isFileImageUploadedByName(file2));
        Assert.assertTrue(homePage.isFileImageUploadedByName(file3));
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        quitDriver();
    }
}
