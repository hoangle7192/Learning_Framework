package actions.pageObjects.users;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;

public class UserChangePasswordPageObject extends BasePage {

    WebDriver driver;

    public UserChangePasswordPageObject(WebDriver driver) {
        this.driver = driver;
    }
}
