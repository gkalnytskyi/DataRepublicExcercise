package com.testexercise.ebayframework;

import org.openqa.selenium.*;

public class BasePage
{
    protected final AutomationFramework af;
    
    public BasePage(AutomationFramework af)
    {
        this.af = af;
    }
    
    final String searchTextInputCss = "form#gh-f input[type='text']";
    final String searchSubmitCss = "form#gh-f input[type='submit']";

    public WebElement searchBox()
    {
        return af.driver().findElement(By.cssSelector(searchTextInputCss));
    }
    
    public WebElement searchSubmit()
    {
        return af.driver().findElement(By.cssSelector(searchSubmitCss));
    }
    
    public void enterSearchQuery(String searchQuery)
    {
        searchBox().clear();
        searchBox().sendKeys(searchQuery);
    }
    
    public void submitSearch()
    {
        searchSubmit().click();
    }
    
    public String getPageUri()
    {
        return af.driver().getCurrentUrl();
    }
}
