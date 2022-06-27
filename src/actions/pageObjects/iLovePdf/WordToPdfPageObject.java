package actions.pageObjects.iLovePdf;

import actions.commons.BasePage;
import actions.commons.PageGenerationManager;
import interfaces.pageUIs.iLovePdf.WordToPdfPageUI;
import org.openqa.selenium.WebDriver;

public class WordToPdfPageObject extends BasePage {

    private final WebDriver driver;

    public WordToPdfPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void UploadWordFile(String fileName) {
        uploadMultipleFiles(driver, fileName);
    }

    public boolean isWordFileUploadSucceed(String fileName) {
        waitForElementVisible(driver, WordToPdfPageUI.FILE_NAME_LOADED, fileName);
        return isElementDisplayed(driver, WordToPdfPageUI.FILE_NAME_LOADED, fileName);
    }

    public DownLoadPageObject convertToPDF() {
        waitForElementClickable(driver, WordToPdfPageUI.CONVERT_TO_PDF_BUTTON);
        clickToElement(driver, WordToPdfPageUI.CONVERT_TO_PDF_BUTTON);
        return PageGenerationManager.getDownLoadPage(driver);
    }
}
