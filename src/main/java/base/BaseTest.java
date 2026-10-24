package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigReader;
import utils.GlobalVars;
import utils.Log;

public class BaseTest {


    protected WebDriver driver;
    private final Log log = Log.getInstance();


    public void setup(){

        log.info("Loading configuration");
        ConfigReader.loadProperties();
        String browser = ConfigReader.getProperty("browser");
        log.info("Browser selected: " + browser);

        if(browser.equalsIgnoreCase("Chrome")){
            driver = new ChromeDriver();
            GlobalVars.getInstance().setDriver(driver);
            log.info("Chrome browser started");
        }

        else if(browser.equalsIgnoreCase("FireFox")){
            driver = new FirefoxDriver();
            GlobalVars.getInstance().setDriver(driver);
            log.info("FireFox browser started");
        }

        else if(browser.equalsIgnoreCase("Edge")){
            driver = new EdgeDriver();
            GlobalVars.getInstance().setDriver(driver);
            log.info("Edge browser started");
        }

        else{
            log.error("Unsupported browser: " + browser);
            throw new IllegalArgumentException("Unsupported Browser" + browser);
        }
    }

    public void maximize(){
        driver.manage().window().maximize();
        log.info("Browser window maximized");
    }

    public void openApplication(){
        String url = ConfigReader.getProperty("url");
        log.info("Opening application: " + url);
        driver.get(url);
        log.info("Application opened successfully");
    }

    public void quitDriver(){
        if (driver != null) {
            log.info("Closing browser");
            driver.quit();
            log.info("Browser closed successfully");
        }
    }
}
