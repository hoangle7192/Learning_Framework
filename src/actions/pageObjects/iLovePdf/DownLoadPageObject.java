package actions.pageObjects.iLovePdf;

import actions.commons.BasePage;
import interfaces.pageUIs.iLovePdf.DownLoadPageUI;
import org.openqa.selenium.WebDriver;

public class DownLoadPageObject extends BasePage {
    private final WebDriver driver;

    public DownLoadPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isConvertToPDFSucceeded() {
        waitForElementVisible(driver, DownLoadPageUI.CONVERT_FILE_SUCCEEDED_MESSAGE);
        return isElementDisplayed(driver, DownLoadPageUI.CONVERT_FILE_SUCCEEDED_MESSAGE);
    }

    public void downLoadPDFFile() {
        deleteAllFileInFolder();
        waitForElementClickable(driver, DownLoadPageUI.DOWNLOAD_PDF_BUTTON);
        clickToElement(driver, DownLoadPageUI.DOWNLOAD_PDF_BUTTON);
    }

}
