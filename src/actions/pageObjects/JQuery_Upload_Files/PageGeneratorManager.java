package actions.pageObjects.JQuery_Upload_Files;

import interfaces.pageUIs.JQuery_Upload_Files.HomePageUI;
import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

    public static HomePageObject getHomePage(WebDriver driver) {
        return new HomePageObject(driver);
    }
}
