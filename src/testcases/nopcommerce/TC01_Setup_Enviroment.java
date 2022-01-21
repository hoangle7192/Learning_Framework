package testcases.nopcommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class TC01_Setup_Enviroment {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        switch (browserName) {
            case "Firefox":
                System.setProperty("webdriver.gecko.driver", projectPath + File.separator + "browserDrivers" + File.separator + "geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", projectPath + File.separator + "browserDrivers" + File.separator + "chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "Edge":
                System.setProperty("webdriver.edge.driver", projectPath + File.separator + "browserDrivers" + File.separator + "msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("This Browser is not support");
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Test
    public void TC_01_ValidateCurrentUrl() {
        // Login Page url matching
        String loginPageUrl = driver.getCurrentUrl();
        Assert.assertEquals(loginPageUrl, "https://demo.nopcommerce.com/");

    }

    @Test
    public void TC_02_ValidatePageTitle() {
        // Login Page titleO
        String loginPageTitle = driver.getTitle();
        Assert.assertEquals(loginPageTitle, "nopCommerce demo store");
    }

    @Test
    public void TC_03_LoginFormDisplayed() {
        // Login form displayed
        Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']")).isDisplayed());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }
}
