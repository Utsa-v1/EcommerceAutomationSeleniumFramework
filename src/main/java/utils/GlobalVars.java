package utils;

import org.openqa.selenium.WebDriver;

public class GlobalVars {

    private static GlobalVars globalVars;
    private WebDriver driver;

    private GlobalVars(){

    }

    public static GlobalVars getInstance(){
        if (globalVars == null) {
            globalVars = new GlobalVars();
        }

        return globalVars;
    }

    public WebDriver getDriver(){
        return driver;
    }

    public void setDriver(WebDriver driver){
        this.driver = driver;
    }


}
