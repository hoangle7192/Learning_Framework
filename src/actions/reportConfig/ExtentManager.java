package actions.reportConfig;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            String workingDir = System.getProperty("user.dir");
            extent = new ExtentReports(workingDir + "/ExtentReportsV2_Screen/ExtentReportResults.html", true);
        }
        return extent;
    }
}
