package generic_pages;

import common_page.CommonSearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonFunctions;


import java.util.ArrayList;
import java.util.List;

public class SearchPage extends CommonSearchPage {


    private CommonFunctions commonFunctions;


    @FindBy(xpath = "//input[contains(@id,'twotabsearchtextbox')]")
    private WebElement searchBar;

    @FindBy(css = "div[class='left-pane-results-container']")
    private WebElement suggestionBox;

    @FindBy(xpath = "//div[contains(@class,'s-suggestion-container')]/div[contains(@class,'s-suggestion') and @role = 'button']")
    private List<WebElement> suggestions;

    @FindBy(xpath = "//h2[contains(@class,'a-size-medium-plus') and contains(text(),'Results')]")
    private WebElement resultPageHeading;

    //Why the constructor?
    //When your test creates the page:
    //SearchPage searchPage = new SearchPage(driver);
    //the existing driver from BaseTest is passed into SearchPage.
    //This is important because we don't create another browser inside the page class.
    //The browser is created by BaseTest; the page object receives the already-created driver.

    public SearchPage(WebDriver driver){
        commonFunctions = new CommonFunctions(driver);
        PageFactory.initElements(driver,this);

    }

    @Override
    public List<Boolean> searchProduct(String key, String suggestionOptions){
        List <Boolean> results = new ArrayList<>();
        boolean suggestionFound = false;

        commonFunctions.click(searchBar,10);
        commonFunctions.sendKeys(searchBar,key);
        commonFunctions.waitForVisibility(suggestionBox,10);
        commonFunctions.waitForVisibilityOfAllElements(suggestions,10);
        for(WebElement suggestion : suggestions ){
            if(commonFunctions.getText(suggestion).equalsIgnoreCase(suggestionOptions)){
                suggestionFound = true;
                commonFunctions.click(suggestion,10);
                break;
            }
        }
        results.add(suggestionFound);
       if(suggestionFound){
           commonFunctions.waitForVisibility(resultPageHeading,10);
           results.add(commonFunctions.getText(resultPageHeading).equalsIgnoreCase("Results"));

       }
       else {
           results.add(false);
       }

        return results;
    }

}
