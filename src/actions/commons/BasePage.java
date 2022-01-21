package actions.commons;

import actions.pageObjects.admin.AdminLoginPageObject;
import actions.pageObjects.users.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public abstract class BasePage {
    /* ---------------------------------Web Browser---------------------------------*/
    protected void openURL(WebDriver driver, String url) {
        driver.get(url);
    }

    protected String getTitlePage(WebDriver driver) {
        return driver.getTitle();
    }

    protected String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    protected String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    protected void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    protected void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    protected Alert waitForAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, longTimeout).until(ExpectedConditions.alertIsPresent());
    }

    protected void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    protected void cancelAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    protected String getTextAlert(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    protected void sendKeyToAlert(WebDriver driver, String value) {
        waitForAlertPresence(driver).sendKeys(value);
    }

    protected void switchToWindowByID(WebDriver driver, String currentWindowID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowID : allWindows) {
            if (!windowID.equals(currentWindowID)) {
                driver.switchTo().window(windowID);
                break;
            }
        }
    }

    protected void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowID : allWindows) {
            driver.switchTo().window(windowID);
            String currentWindowTitle = getTitlePage(driver);
            if (currentWindowTitle.equals(expectedTitle)) {
                break;
            }
        }
    }

    protected void closeWindow(WebDriver driver) {
        driver.close();
    }

    protected void closeAllWindowWithoutParent(WebDriver driver, String expectedTitle) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowID : allWindows) {
            driver.switchTo().window(windowID);
            String currentWindowTitle = getTitlePage(driver);
            if (!currentWindowTitle.equals(expectedTitle)) {
                closeWindow(driver);
            }
            driver.switchTo().window(expectedTitle);
        }
    }

    public void sleepInSecond(long timeoutInSecond) {
        try {
            Thread.sleep(timeoutInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void sleepInMiLiSecond(long timeoutInMiLiSecond) {
        try {
            Thread.sleep(timeoutInMiLiSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* ---------------------------------Web Element---------------------------------*/


    // locatorType: id=/ css=/ xpath=/ name=/ class=
    // locatorType: ID=/ CSS=/ XPATH=/ NAME=/ CLASS=
    // locatorType: Id=/ Css=/ Xpath=/ Name=/ Class=
    protected By getLocator(String locatorType) {
        By by = null;
        if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
            locatorType = locatorType.substring(3);
            by = By.id(locatorType);
        } else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
            locatorType = locatorType.substring(4);
            by = By.cssSelector(locatorType);
        } else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=")) {
            locatorType = locatorType.substring(6);
            by = By.className(locatorType);
        } else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=")) {
            locatorType = locatorType.substring(5);
            by = By.name(locatorType);
        } else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
            locatorType = locatorType.substring(6);
            by = By.xpath(locatorType);
        } else {
            throw new RuntimeException("Locator Type Is Not Support");
        }
        return by;
    }

    // Nếu như truyền locator là Xpath thì work được, còn lại là ko work
    private String getDynamicXpath(String locatorType, String... dynamicValues) {
        //System.out.println("locator type before = " + locatorType);
        if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
            locatorType = String.format(locatorType, (Object[]) dynamicValues);
            return locatorType;
        } else {
            return locatorType;
        }
 /*       for(String value: dynamicValues) {
            System.out.println("Values map to locator = " + value);
        }
        System.out.println("locator type after = " + locatorType);

        return locatorType;*/
    }


    protected WebElement getWebElement(WebDriver driver, String locatorType) {
        return driver.findElement(getLocator(locatorType));
    }

    protected List<WebElement> getListWebElements(WebDriver driver, String locatorType) {
        return driver.findElements(getLocator(locatorType));
    }


    protected void clickToElement(WebDriver driver, String locatorType) {
        getWebElement(driver, locatorType).click();
    }

    protected void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
        getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
    }


    protected void sendKeyToElement(WebDriver driver, String locatorType, String value) {
        getWebElement(driver, locatorType).clear();
        getWebElement(driver, locatorType).sendKeys(value);
    }

    protected void sendKeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
        getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).clear();
        getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).sendKeys(textValue);
    }

    protected void selectItemInDefaultDropDown(WebDriver driver, String locatorType, String itemText) {
        new Select(getWebElement(driver, locatorType)).selectByVisibleText(itemText);
    }

    protected String getSelectedInDefaultDropDown(WebDriver driver, String locatorType) {
        return new Select(getWebElement(driver, locatorType)).getFirstSelectedOption().getText();
    }

    protected Boolean isDropDownMultiple(WebDriver driver, String locatorType) {
        return new Select(getWebElement(driver, locatorType)).isMultiple();
    }

    protected void selectItemInCustomDropDown(WebDriver driver, String parentXpath, String childXpath, String expectedItemText) {
        clickToElement(driver, parentXpath);
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getLocator(childXpath)));
        List<WebElement> allItemText = getListWebElements(driver, childXpath);
        for (WebElement itemText : allItemText) {
            if (itemText.getText().trim().equals(expectedItemText)) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", itemText);
                sleepInSecond(1);
                itemText.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    protected String getElementAttributeValue(WebDriver driver, String locatorType, String attributeName) {
        return getWebElement(driver, locatorType).getAttribute(attributeName);
    }

    protected String getElementText(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).getText();
    }

    protected String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
    }

    protected String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
        return getWebElement(driver, locatorType).getCssValue(propertyName);
    }

    protected String getHeXaColorFromRgba(String rgbaColor) {
        return Color.fromString(rgbaColor).asHex();
    }

    protected int getElementSizes(WebDriver driver, String locatorType) {
        return getListWebElements(driver, locatorType).size();
    }

    protected int getElementSizes(WebDriver driver, String locatorType, String... dynamicValues) {
        return getListWebElements(driver, getDynamicXpath(locatorType, dynamicValues)).size();
    }

    protected void checkToRadioOrCheckbox(WebDriver driver, String locatorType) {
        if (!isElementSelected(driver, locatorType)) {
            getWebElement(driver, locatorType).click();
        }
    }

    protected void unCheckToRadioOrCheckbox(WebDriver driver, String locatorType) {
        if (isElementSelected(driver, locatorType)) {
            getWebElement(driver, locatorType).click();
        }
    }

    protected Boolean isElementDisplayed(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).isDisplayed();
    }

    protected Boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
    }

    protected Boolean isElementSelected(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).isSelected();
    }

    protected Boolean isElementEnable(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).isEnabled();
    }

    protected void switchToFrame(WebDriver driver, String locatorType) {
        driver.switchTo().frame(getWebElement(driver, locatorType));
    }

    protected void switchToDefaultContentPage(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    protected void hoverMouseToElement(WebDriver driver, String locatorType) {
        new Actions(driver).moveToElement(getWebElement(driver, locatorType)).perform();
    }

    protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
        new Actions(driver).sendKeys(getWebElement(driver, locatorType), key).perform();
    }

    protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
        new Actions(driver).sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
    }

    /*JavaScriptExecutor*/

    protected Object executeForBrowser(WebDriver driver, String javaScript) {
        //jsExecutor = (JavascriptExecutor) driver;
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    protected String getInnerText(WebDriver driver) {
        //jsExecutor = (JavascriptExecutor) driver;
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
    }

    protected boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
        //jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    protected void scrollToBottomPage(WebDriver driver) {
        //jsExecutor = (JavascriptExecutor) driver;
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    protected void navigateToUrlByJS(WebDriver driver, String url) {
        //jsExecutor = (JavascriptExecutor) driver;
        ((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
    }

    protected void highlightElement(WebDriver driver, String locatorType) {
        //jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, locatorType);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        //jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    protected void clickToElementByJS(WebDriver driver, String locatorType) {
        //jsExecutor = (JavascriptExecutor) driver;
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locatorType));
    }

    protected void scrollToElement(WebDriver driver, String locatorType) {
        //jsExecutor = (JavascriptExecutor) driver;
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
    }

    protected void sendKeyToElementByJS(WebDriver driver, String locatorType, String sendKeyValue) {
        //jsExecutor = (JavascriptExecutor) driver;
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + sendKeyValue + "')", getWebElement(driver, locatorType));
    }

    protected void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
        //jsExecutor = (JavascriptExecutor) driver;
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
    }

    protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        //jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    protected String getElementValidationMessage(WebDriver driver, String locatorType) {
        //jsExecutor = (JavascriptExecutor) driver;
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
    }

    protected boolean isImageLoaded(WebDriver driver, String locatorType) {
        //jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                getWebElement(driver, locatorType));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    /*Wait*/

    /*Dung cho ham getText, check Display*/
    protected void waitForElementVisible(WebDriver driver, String locatorType) {
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getLocator(locatorType)));
    }

    protected void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    protected void waitForElementClickable(WebDriver driver, String locatorType) {
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getLocator(locatorType)));
    }

    protected void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    protected void waitForElementInvisible(WebDriver driver, String locatorType) {
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getLocator(locatorType)));
    }

    protected void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForAllElementVisible(WebDriver driver, String locatorType) {
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getLocator(locatorType)));
    }

    public void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    protected void waitForAllElementInvisible(WebDriver driver, String locatorType) {
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, locatorType)));
    }

    protected void waitForAllElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, getDynamicXpath(locatorType, dynamicValues))));
    }

    //-----------TC05_Switch_Page.xml---------------//
    // User Site: bài TC05: Switch Page

    public UserAddressPageObject openAddressesPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.ADDRESSES_PAGE_LINK);
        clickToElement(driver, BasePageUI.ADDRESSES_PAGE_LINK);
        return PageGenerationManager.getUserAddressesPage(driver);
    }

    public UserOrdersPageObject openOrdersPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.ORDERS_PAGE_LINK);
        clickToElement(driver, BasePageUI.ORDERS_PAGE_LINK);
        return PageGenerationManager.getUserOrderPage(driver);
    }

    public UserDownloadableProductsPageObject openDownloadableProductsPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.DOWNLOADADABLE_PRODUCT_PAGE_LINK);
        clickToElement(driver, BasePageUI.DOWNLOADADABLE_PRODUCT_PAGE_LINK);
        return PageGenerationManager.getUserDownloadableProductsPage(driver);
    }

    public UserBackInStockSubscriptionsPageObject openBackInStockSubscriptionsPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.BACK_IN_STOCK_SUBSCRIPTIONS_PAGE_LINK);
        clickToElement(driver, BasePageUI.BACK_IN_STOCK_SUBSCRIPTIONS_PAGE_LINK);
        return PageGenerationManager.getUserBackInStockSubscriptionsPage(driver);
    }

    public UserRewardPointPageObject openRewardPointPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.REWARD_POINT_PAGE_LINK);
        clickToElement(driver, BasePageUI.REWARD_POINT_PAGE_LINK);
        return PageGenerationManager.getUserRewardPointPage(driver);
    }

    public UserChangePasswordPageObject openChangePasswordPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.CHANGE_PASSWORD_PAGE_LINK);
        clickToElement(driver, BasePageUI.CHANGE_PASSWORD_PAGE_LINK);
        return PageGenerationManager.getUserChangePasswordPage(driver);
    }

    public UserMyProductReviewsPageObject openMyProductReviewsPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEWS_PAGE_LINK);
        clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEWS_PAGE_LINK);
        return PageGenerationManager.getUserMyProductReviews(driver);
    }

    public UserCustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.CUSTOMER_INFO_PAGE_LINK);
        clickToElement(driver, BasePageUI.CUSTOMER_INFO_PAGE_LINK);
        return PageGenerationManager.getUserCustomerInfoPage(driver);
    }

    public UserHomePageObject clickToUserLogoutLink(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.LOGOUT_USER_LINK);
        clickToElement(driver, BasePageUI.LOGOUT_USER_LINK);
        return PageGenerationManager.getUserHomePage(driver);
    }

    // Dynamic Locator: dùng cho bài TC07_Dynamic Trường hợp ít pages
    public BasePage openDynamicPagesAtMyAccountByNameItPage(WebDriver driver, String pageName) {
        waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
        clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
        switch (pageName) {
            case "Customer info":
                return PageGenerationManager.getUserCustomerInfoPage(driver);
            case "Addresses":
                return PageGenerationManager.getUserAddressesPage(driver);
            case "My product reviews":
                return PageGenerationManager.getUserMyProductReviews(driver);
            case "Reward points":
                return PageGenerationManager.getUserRewardPointPage(driver);
            case "Orders":
                return PageGenerationManager.getUserOrderPage(driver);
            case "Downloadable products":
                return PageGenerationManager.getUserDownloadableProductsPage(driver);
            case "Back in stock subscriptions":
                return PageGenerationManager.getUserBackInStockSubscriptionsPage(driver);
            case "Change password":
                return PageGenerationManager.getUserChangePasswordPage(driver);
            default:
                throw new RuntimeException("Invalid page name at My Account area.");
        }
    }

    // Dynamic Locator: dùng cho bài TC07_Dynamic Trường hợp nhiều pages
    public void openDynamicPagesAtMyAccountByNameNhieuPage(WebDriver driver, String pageName) {
        waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
        clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
    }


    // Admin Site: dùng cho bài TC06: Switch Role
    public AdminLoginPageObject clickToAdminLogoutLink(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.LOGOUT_ADMIN_LINK);
        clickToElement(driver, BasePageUI.LOGOUT_ADMIN_LINK);
        return PageGenerationManager.getAdminLoginPage(driver);
    }


    private long shortTimeout = 5;
    private long longTimeout = 30;


}
