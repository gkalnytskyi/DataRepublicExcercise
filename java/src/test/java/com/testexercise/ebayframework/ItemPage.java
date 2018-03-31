package com.testexercise.ebayframework;

import org.openqa.selenium.*;

public class ItemPage extends BasePage
{

    public ItemPage(AutomationFramework af)
    {
        super(af);
    }
    
    private final String addToCartId = "isCartBtn_btn";
    private final String itemTitleId = "itemTitle";
    private final String itemPriceId = "prcIsum";
    
    public void addItemtoCart()
    {
        af.driver().findElement(By.id(addToCartId)).click();
    }
    
    public String getItemTitle()
    {
        return af.driver().findElement(By.id(itemTitleId)).getText();
    }
    
    public String getItemPrice()
    {
        return af.driver().findElement(By.id(itemPriceId)).getText();
    }
}
