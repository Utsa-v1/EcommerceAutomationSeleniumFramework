package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtils {
    private TakesScreenshot screenshot;

    public ScreenshotUtils(WebDriver driver) {
        screenshot = (TakesScreenshot) driver;
    }


    public File takeScreenshot(String testName) throws IOException {

        File source = screenshot.getScreenshotAs(OutputType.FILE);

        File screenshotDirectory = new File("screenshots");

        if (!screenshotDirectory.exists()) {
            screenshotDirectory.mkdirs();
        }

        File destination =
                new File(screenshotDirectory, testName + ".png");

        Files.copy(
                source.toPath(),
                destination.toPath(),
                StandardCopyOption.REPLACE_EXISTING
        );

        return destination;
    }
}
