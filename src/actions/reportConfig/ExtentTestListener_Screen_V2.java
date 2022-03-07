package actions.reportConfig;



import actions.commons.BaseTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.testng.*;


public class ExtentTestListener_Screen_V2 extends BaseTest implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        System.out.println("---------- " + context.getName() + " STARTED test ----------");
        context.setAttribute("WebDriver", this.getDriverInstance());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("---------- " + context.getName() + " FINISHED test ----------");
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("---------- " + result.getName() + " STARTED test ----------");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("---------- " + result.getName() + " SUCCESS test ----------");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("---------- " + result.getName() + " FAILED test ----------");

        Object testClass = result.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriverInstance();
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
        ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed", ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("---------- " + result.getName() + " SKIPPED test ----------");
        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("---------- " + result.getName() + " FAILED WITH SUCCESS PERCENTAGE test ----------");
    }
}
