package testcases.nopcommerce;

import actions.commons.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class TC10_Assert_Setup_Environment extends BaseTest {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        //Firefox
        System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();

        //Chrome123
        //System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        //driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://demo.guru99.com/v4/");
    }

    @Test
    public void TC_01_ValidateCurrentUrl() {
        // Login Page url matching
        System.out.println("Step01");
        String loginPageUrl = driver.getCurrentUrl();
        verifyEquals(loginPageUrl, "http://demo.guru99.com/v4/a");

        // Login Page titleO
        System.out.println("Step02");
        String loginPageTitle = driver.getTitle();
        verifyEquals(loginPageTitle, "Guru99 Bank Home Page...");

        // Login form displayed
        System.out.println("Step03");
        verifyTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());

        System.out.println("Step04");
        verifyFalse(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
