package actions.reportConfig;

import actions.commons.BaseTest;
import actions.commons.GlobalConstants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportNGListener extends BaseTest implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        System.out.println("---------- " + context.getName() + " STARTED test ----------");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("---------- " + context.getName() + " FINISHED test ----------");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("---------- " + result.getName() + " STARTED test ----------");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("---------- " + result.getName() + " SUCCESS test ----------");
    }

    // --------------------------------ReportNGListener Image File--------------------------------
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("---------- " + result.getName() + " FAILED test ----------");
        System.setProperty("org.uncommons.reportng.escape-output", "false");

        Object testClass = result.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriverInstance(); // lấy ra Driver từ basetest

        String screenshotPath = captureScreenshot(webDriver, result.getName());
        Reporter.getCurrentTestResult();
        Reporter.log("<br><a target=\"_blank\" href=\"file:///" + screenshotPath + "\">" + "<img src=\"file:///" + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
        Reporter.setCurrentTestResult(null);
    }

    public String captureScreenshot(WebDriver driver, String screenshotName) { //screenshotName là tên testcase
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenPath = GlobalConstants.REPORT_NG_SCREEN_SHOT + screenshotName + "_" + formatter.format(calendar.getTime()) + ".jpg";
            FileUtils.copyFile(source, new File(screenPath));
            return screenPath;
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return e.getMessage();
        }
    }

    // --------------------------------ReportNGListener Image Base64--------------------------------

/*    @Override
    public void onTestFailure(ITestResult result) {
        try {
            System.setProperty("org.uncommons.reportng.escape-output", "false");

            Object testClass = result.getInstance();
            WebDriver driver = ((BaseTest) testClass).getDriverInstance();

            String screenshotPath = captureScreenshot(driver);
            Reporter.getCurrentTestResult();

            Reporter.log("<br><a href=\"data:image/png;base64," + screenshotPath + "\">" + "<img src=\"data:image/png;base64," + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
            Reporter.setCurrentTestResult(null);
        } catch (NoSuchSessionException e) {
            e.printStackTrace();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }*/

    public String captureScreenshot(WebDriver driver) {
        String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        return screenshotBase64;
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("---------- " + result.getName() + " SKIPPED test ----------");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("---------- " + result.getName() + " FAILED WITH SUCCESS PERCENTAGE test ----------");
    }

}
