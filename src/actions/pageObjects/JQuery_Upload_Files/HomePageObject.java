package actions.pageObjects.JQuery_Upload_Files;

import actions.commons.BasePage;
import interfaces.pageUIs.JQuery_Upload_Files.HomePageUI;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePageObject extends BasePage {

    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }


    public boolean isFileLoadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_NAME_LOADED, fileName);
        return  isElementDisplayed(driver, HomePageUI.FILE_NAME_LOADED, fileName);
    }

    public boolean isFileLinkUploadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_NAME_UPLOADED_LINK, fileName);
        return  isElementDisplayed(driver, HomePageUI.FILE_NAME_UPLOADED_LINK, fileName);
    }

    public void clickToStartButton() {
        List<WebElement> startButtons = getListWebElements(driver, HomePageUI.START_BUTTON);
        for(WebElement startButton : startButtons) {
            startButton.click();
            sleepInSecond(2);
        }
    }

    public boolean isFileImageUploadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_NAME_UPLOADED_IMG, fileName);
        return  isImageLoaded(driver, HomePageUI.FILE_NAME_UPLOADED_IMG, fileName );
    }
}
