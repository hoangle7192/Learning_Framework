package actions.pageObjects.facebook;

import actions.commons.BasePage;
import interfaces.pageUIs.facebook.FacebookLoginPageUI;
import org.openqa.selenium.WebDriver;

public class FacebookLoginPageObject extends BasePage {
    WebDriver driver;

    public FacebookLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToCreateNewAccountButton() {
        waitForElementClickable(driver, FacebookLoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
        clickToElement(driver, FacebookLoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
    }

    public boolean isEmailTextBoxDisplayed() {
        waitForElementVisible(driver, FacebookLoginPageUI.MOBILE_NUMBER_OR_EMAIL_ADDRESS_INPUT_PLACE);
        return isElementDisplayed(driver, FacebookLoginPageUI.MOBILE_NUMBER_OR_EMAIL_ADDRESS_INPUT_PLACE);
    }

    public void enterToEmailTextBox(String value) {
        waitForElementVisible(driver, FacebookLoginPageUI.MOBILE_NUMBER_OR_EMAIL_ADDRESS_INPUT_PLACE);
        sendKeyToElement(driver, FacebookLoginPageUI.MOBILE_NUMBER_OR_EMAIL_ADDRESS_INPUT_PLACE, value);
    }

    public boolean isReEnterEmailTextBoxDisplayed() {
        waitForElementVisible(driver, FacebookLoginPageUI.RE_ENTER_EMAIL_ADDRESS_INPUT_PLACE);
        return isElementDisplayed(driver, FacebookLoginPageUI.RE_ENTER_EMAIL_ADDRESS_INPUT_PLACE);
    }

    public boolean isReEnterEmailTextBoxUnDisplayed() {
        return isElementUnDisplayed(driver, FacebookLoginPageUI.RE_ENTER_EMAIL_ADDRESS_INPUT_PLACE);
    }


}
