package tests;

import base.BaseTest;
import generic_pages.ProductPage;
import generic_pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;


public class ProductPageTest extends BaseTest {

    @BeforeMethod
    public void startTest(){
        setup();
        maximize();
        openApplication();
    }

    @Test
    public void verifyProductTitle(){
        SearchPage sp = new SearchPage(driver);
        ProductPage pg = new ProductPage(driver);

        sp.searchProduct("iphone", "iphone 18");
        Assert.assertTrue(pg.verifyProductTitle());

    }
    @Test
    public void addToCart(){
        SearchPage sp = new SearchPage(driver);
        ProductPage pg = new ProductPage(driver);

        sp.searchProduct("iphone", "iphone 18");
        Assert.assertTrue(pg.addToCart());

    }

    @AfterMethod
    public void closeTest(){
        quitDriver();
    }
}
