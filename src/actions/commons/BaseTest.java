package actions.commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    private WebDriver driver;

    protected WebDriver getBrowserDriver(String browserName, String url) {

        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

        switch (browser) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.addExtensions(new File(GlobalConstants.BROWSER_EXTENSION + "UltraSurf-VPN_v1.6.0.crx"));
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case IE:
                WebDriverManager.iedriver().arch64().setup();
                driver = new InternetExplorerDriver();
                break;
            default:
                throw new RuntimeException("This browser is not support");
        }

        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIME_OUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }

    protected WebDriver getBrowserDriverifelse(String browserName, String enviromentName) {

        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        if (browser == BrowserList.FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if(browser == BrowserList.CHROME) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if(browser == BrowserList.H_CHROME) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("window-size=1920x1080");
            driver = new ChromeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIME_OUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(getEnviromentUrl(enviromentName));
        return driver;
    }

    private String getEnviromentUrl(String enviromentName) {
        String envUrl = null;
        EnviromentList enviroment = EnviromentList.valueOf(enviromentName.toUpperCase());
        if (enviroment == EnviromentList.DEV) {
            envUrl = "dev url";
        } else if(enviroment == EnviromentList.TESTING) {
            envUrl = "test url";
        } else if(enviroment == EnviromentList.STAGING) {
            envUrl = "staging url";
        } else if(enviroment == EnviromentList.PRODUCTION) {
            envUrl = "product url";
        }
        return envUrl;
    }

    protected void quitDriver() {
        driver.quit();
    }

    protected String generateFakeNumber() {
        Random random = new Random();
        return random.nextInt(999999) + "@gmail.com";
    }


    /*Hàm Assert*/
    private boolean checkTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            System.out.println(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            System.out.println(" -------------------------- FAILED -------------------------- ");
            pass = false;

            // Add lỗi vào ReportNG
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyTrue(boolean condition) {
        return checkTrue(condition);
    }

    private boolean checkFailed(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            System.out.println(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            System.out.println(" -------------------------- FAILED -------------------------- ");
            pass = false;

            // Add lỗi vào ReportNG
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        return checkFailed(condition);
    }

    private boolean checkEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            System.out.println(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            System.out.println(" -------------------------- FAILED -------------------------- ");

            // Add lỗi vào ReportNG
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        return checkEquals(actual, expected);
    }
    /*Hàm Assert*/

}
