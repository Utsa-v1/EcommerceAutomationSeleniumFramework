package generic_pages;

import common_page.CommonProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonFunctions;

public class ProductPage extends CommonProductPage {

    private CommonFunctions commonFunctions;

    public ProductPage(WebDriver driver){
        commonFunctions = new CommonFunctions(driver);
        PageFactory.initElements(driver,this);
    }
    private String brandName;
    private String productNameText;

    @FindBy(xpath="//span[contains(text(),'S2 FE 5G (Titan Black, 6GB RAM, 128GB Storage) with No Cost EMI/Additional Exchange Offers')]/ancestor::div[@class='a-section']")
    private WebElement scrollToTargetProduct;

    @FindBy(xpath = "//a[contains(@class,'a-link-normal')]//span[contains(text(),'S2 FE 5G (Titan Black, 6GB RAM, 128GB Storage) with No Cost EMI/Additional Exchange Offers')]")
    private WebElement productName;

    @FindBy(xpath = "//div[contains(@class,'a-color-secondary')]//span[contains(@class,'a-size-medium') and text()='vivo']")
    private WebElement productBrand;

    @FindBy(xpath = "//h1[@id = 'title']/span[contains(text(),'vivo S2 FE 5G (Titan Black, 6GB RAM, 128GB Storage) with No Cost EMI/Additional Exchange Offers')]")
    private WebElement productPageHeading;

    @FindBy(css = "span[class='a-button-inner'] input[id='add-to-cart-button']")
    private WebElement addToCart;

    @FindBy(xpath = "//h1[contains(@class,'a-size-medium-plus') and contains(text(),'Added to cart')]")
    private WebElement afterAddToCart;


    @Override
    public boolean verifyProductTitle() {

        productPageHeadingMethod();
        String expectedProductTitle = brandName + " " + productNameText;
        String actualProductTitle = commonFunctions.getText(productPageHeading);

        return actualProductTitle.equalsIgnoreCase(expectedProductTitle);

    }

    @Override
    public boolean addToCart(){
        productPageHeadingMethod();
        commonFunctions.scrollElement(addToCart,10);
        commonFunctions.click(addToCart,10);

        return commonFunctions.elementDisplayed(afterAddToCart,10);

    }

    public void productPageHeadingMethod(){

        String parent = commonFunctions.parentWindow();
        commonFunctions.scrollElement(scrollToTargetProduct,10);
        commonFunctions.waitForVisibility(productBrand,10);
        commonFunctions.highlightElement(productBrand);
        brandName = commonFunctions.getText(productBrand);
        commonFunctions.highlightElement(productName);
        productNameText = commonFunctions.getText(productName);
        commonFunctions.click(productName,10);
        commonFunctions.windowHandles(parent);
        commonFunctions.waitForVisibility(productPageHeading,10);
        commonFunctions.highlightElement(productPageHeading);

    }


}
