package Listeners;




import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

public class ExtentReporterListener implements ITestListener {

    private static final String OUTPUT_FOLDER = "./reports/";
    private static final String FILE_NAME = "ExtentReport.html";

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Create and configure ExtentReports
    private static ExtentReports createReporter() {
        try {
            Path path = Paths.get(OUTPUT_FOLDER);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ExtentSparkReporter spark = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
        spark.config().setReportName("Automation Test Report");
        spark.config().setDocumentTitle("Extent Report");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Add system info
        extent.setSystemInfo("Author", "QA Team");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extent;
    }

    // Expose current ExtentTest for LoggerUtil
    public static ExtentTest getExtentTest() {
        return test.get();
    }

    @Override
    public synchronized void onStart(ITestContext context) {
        extent = createReporter();
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
        test.remove();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();

        ExtentTest extentTest = extent.createTest(methodName, description);
        extentTest.assignCategory(result.getTestClass().getName());

        extentTest.getModel().setStartTime(getTime(result.getStartMillis()));
        test.set(extentTest);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
        test.get().fail("Test failed");
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        test.get().skip("Test skipped");
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}


