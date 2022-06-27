package testcases.uploadDownload;

import actions.commons.BaseTest;
import actions.commons.PageGenerationManager;
import actions.pageObjects.iLovePdf.DownLoadPageObject;
import actions.pageObjects.iLovePdf.WordToPdfPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC17_upLoadDownLoadFile extends BaseTest {
    private WebDriver driver;
    private WordToPdfPageObject wordToPdfPage;
    private DownLoadPageObject downLoadPage;

    private String file1 = "testUpload.docx";
    private String processText = "Converting WORD to PDF...";


    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {
        driver = getBrowserDriver(browser, url);
        wordToPdfPage = PageGenerationManager.getWordToPDFPage(driver);
    }

    @Test
    public void TC_01_Upload_File() {
        // upload Word file
        wordToPdfPage.UploadWordFile(file1);

        // verify upload success
        Assert.assertTrue(wordToPdfPage.isWordFileUploadSucceed(file1));
        wordToPdfPage.sleepInSecond(10);

        // Convert To PDF
        downLoadPage = wordToPdfPage.convertToPDF();

        // verify convert to PDF file Succeeded
        Assert.assertTrue(downLoadPage.isConvertToPDFSucceeded());

        // DownLoad PDF file
        downLoadPage.downLoadPDFFile();

        // verify file has been downloaded
        Assert.assertTrue(downLoadPage.isFileDownloaded(file1));




    }

    @AfterClass
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
