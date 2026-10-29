package listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;
import utils.DataReader;
import utils.ExtentManager;
import utils.GlobalVars;
import utils.Log;
import utils.ScreenshotUtils;

import java.io.IOException;

public class TestListener implements ITestListener {

    private ExtentReports extentReports;
    private ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {

        extentReports = ExtentManager.getInstance();

        Log.getInstance().info("Test Suite Started");
    }

    @Override
    public void onTestStart(ITestResult result) {

        String testName =
                result.getMethod().getMethodName();

        String className =
                result.getTestClass()
                        .getRealClass()
                        .getSimpleName();

        // Create Extent test
        extentTest = extentReports.createTest(testName);

        // Read test execution status from Excel
        DataReader dataReader = new DataReader();

        String status =
                dataReader.getTestStatus(
                        className,
                        testName
                );

        if (status.equalsIgnoreCase("skip")) {

            Log.getInstance().info(
                    "Test skipped from Excel: " + testName
            );

            extentTest.skip(
                    "Test marked as SKIP in testdata.xlsx"
            );

            throw new SkipException(
                    "Test marked as SKIP in testdata.xlsx"
            );
        }

        Log.getInstance().info(
                "Test Started: " + testName
        );

        extentTest.info("Test Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        String testName =
                result.getMethod().getMethodName();

        Log.getInstance().info(
                "Test Passed: " + testName
        );

        extentTest.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String testName =
                result.getMethod().getMethodName();

        Log.getInstance().error(
                "Test Failed: " + testName
        );

        extentTest.fail(
                result.getThrowable()
        );

        ScreenshotUtils screenshotUtils =
                new ScreenshotUtils(
                        GlobalVars.getInstance().getDriver()
                );

        try {

            screenshotUtils.takeScreenshot(testName);

            extentTest.addScreenCaptureFromPath(
                    "screenshots/" + testName + ".png"
            );

            Log.getInstance().info(
                    "Screenshot captured for: " + testName
            );

        } catch (IOException e) {

            Log.getInstance().error(
                    "Failed to capture screenshot for: "
                            + testName
            );
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        String testName =
                result.getMethod().getMethodName();

        Log.getInstance().info(
                "Test Skipped: " + testName
        );

        if (extentTest != null) {
            extentTest.skip("Test Skipped");
        }
    }

    @Override
    public void onFinish(ITestContext context) {

        Log.getInstance().info("Test Suite Finished");

        if (extentReports != null) {
            extentReports.flush();
        }
    }
}