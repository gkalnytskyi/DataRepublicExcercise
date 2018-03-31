package com.testexercise.ebay;

import java.net.*;
import java.util.*;

import org.openqa.selenium.*;
import org.testng.*;
import org.testng.annotations.*;

import com.testexercise.ebayframework.*;

public class EbayTest {
    
    WebDriver driver;
    AutomationFramework af;
    Steps steps;
    
    @Parameters({ "browser", "driverpath" })
    @BeforeMethod
    public void beforeMethod(String browser, String driverpath)
            throws URISyntaxException 
    {
        driver = WebDriverFactory.
                getDriver(
                        WebBrowserType.valueOf(browser.toUpperCase()),
                        driverpath);
        af = new AutomationFramework(driver, "https://www.ebay.com.au");
        steps = new Steps(af);
    }

    @AfterMethod
    public void afterMethod()
    {
        if (driver != null)
        {
            driver.close();
            try
            {
                // an issue with geckodriver
                // https://github.com/mozilla/geckodriver/issues/966
                driver.quit();
            }
            catch (Exception ex) { }
        }
    }
    
    @Test
    public void Search_AddItemsToCart_Validate() throws URISyntaxException
    {
        steps.goToEbayWebSite();
        
        steps.searchFor("Asus GeForce GTX 1070");
        steps.filterOnlyBuyItNowItems();
        steps.openFirstSearchResult();
        
        ItemDetails item1 = steps.getDetailsFromItemPage();
        
        steps.addItemToCart();
        
        steps.searchFor("sewing pins");
        steps.filterOnlyBuyItNowItems();
        steps.openFirstSearchResult();
        
        ItemDetails item2 = steps.getDetailsFromItemPage();
        
        steps.addItemToCart();
        
        List<ItemDetails> itemsInCart = steps.getItemsInCart();
        
        String[] itemIds = itemsInCart.
                stream().map(x -> {
                    try
                    {
                        return x.itemId();
                    }
                    catch (URISyntaxException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return null;
                }).toArray(String[]::new);
        
        Assert.assertEquals(
                new HashSet<String>(Arrays.asList(itemIds)),
                new HashSet<String>(
                        Arrays.asList(
                                item1.itemId(),
                                item2.itemId())));
        
        ItemDetails cartItem1 = itemsInCart.stream().
                filter(x -> {
                    try
                    {
                        return x.itemId() == item1.itemId();
                    }
                    catch (URISyntaxException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return false;
                }).
                findFirst().get();
        
        ItemDetails cartItem2 = itemsInCart.stream().
                filter(x -> {
                    try
                    {
                        return x.itemId() == item2.itemId();
                    }
                    catch (URISyntaxException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return false;
                }).
                findFirst().get();
        
        Assert.assertEquals(cartItem1.title(), item1.title());
        Assert.assertEquals(cartItem1.price(), item1.price());
        Assert.assertEquals(cartItem2.title(), item2.title());
        Assert.assertEquals(cartItem2.price(), item2.price());
    }
}
