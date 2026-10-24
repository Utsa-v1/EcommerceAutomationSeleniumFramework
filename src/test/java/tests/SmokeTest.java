package tests;

import base.BaseTest;
import generic_pages.SearchPage;
import listener.TestListener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners(TestListener.class)
public class SmokeTest extends BaseTest {

    @BeforeMethod
    public void startTest(){
        setup();
        maximize();
        openApplication();
    }

    @Test
    public void verifyApplicationLaunch(){
        SearchPage sp = new SearchPage(driver);
        List <Boolean> result = sp.searchProduct("iphone", "iphone 18");
        Assert.assertTrue(result.get(0));
        Assert.assertTrue(result.get(1));

    }


    @AfterMethod
    public void closeTest(){
        quitDriver();
    }

}
