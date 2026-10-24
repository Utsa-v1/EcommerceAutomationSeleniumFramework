package listener;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.GlobalVars;
import utils.Log;
import utils.ScreenshotUtils;

import java.io.IOException;

public class TestListener implements ITestListener {


    @Override
    public void onTestStart(ITestResult result){
        Log.getInstance().info("Test Started:"+ result.getMethod().getMethodName());

    }

    @Override
    public void onTestSuccess(ITestResult result){
      Log.getInstance().info("Test Passed:"+ result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        Log.getInstance().error("Test Failed: " + testName);

        ScreenshotUtils screenshotUtils =
                new ScreenshotUtils(GlobalVars.getInstance().getDriver());

        try {
            screenshotUtils.takeScreenshot(testName);
        } catch (IOException e) {
            Log.getInstance().error(
                    "Failed to capture screenshot for " + testName
            );
        }
    }

    @Override
    public void onTestSkipped(ITestResult result){
        Log.getInstance().info("Test Skipped:"+ result.getMethod().getMethodName());
    }


}
