package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class CommonFunctions {

    private WebDriver driver;
    private WebDriverWait wait;
    JavascriptExecutor js;

    public CommonFunctions(WebDriver driver){
        this.driver = driver;
        js = (JavascriptExecutor)driver;
    }

    public void click(WebElement element, int timeouts){
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void waitForVisibility(WebElement element, int timeouts){
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForVisibilityOfAllElements(List<WebElement> element, int timeouts){
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public String getText(WebElement element){
        return element.getText();
    }

    public void sendKeys(WebElement element, String key){
        element.sendKeys(key);
    }

    public void scrollElement(WebElement element, int timeouts){
        wait = new WebDriverWait(driver,Duration.ofSeconds(timeouts));
        js.executeScript("arguments[0].scrollIntoView(true);",element);
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public void highlightElement(WebElement element){
        js.executeScript("arguments[0].style.border='3px solid red';", element);
    }

    public boolean elementDisplayed(WebElement element, int timeouts){
        wait = new WebDriverWait(driver,Duration.ofSeconds(timeouts));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }

    public void windowHandles(String parent){
        Set<String> windows = driver.getWindowHandles();
        if(windows.size()>1) {
            for (String window : windows) {
                if (!window.equals(parent)) {
                    driver.switchTo().window(window);
                    break;
                }
            }
        }
    }

    public String parentWindow(){
        return driver.getWindowHandle();
    }

    public void switchToParent(String parent){
            driver.switchTo().window(parent);
    }

    public void quitDriver(){
        driver.quit();
    }
}
