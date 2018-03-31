package com.testexercise.ebayframework;

import java.util.*;

import org.openqa.selenium.*;

public class SearchResultsPage extends BasePage
{
    public SearchResultsPage(AutomationFramework af)
    {
        super(af);
    }
    
    private final String resultListId = "ListViewInner";
    private final String resultListItemCss = "#ListViewInner > li";
    private final String itemListingTypeCss = "div.pnl-b.frmt";
    
    public WebElement resultsList()
    {
        return af.driver().findElement(By.id(resultListId));
    }
    
    public void viewOnlyBuyItNowItems()
    {
        af.driver().
            findElement(By.cssSelector(itemListingTypeCss)).
            findElement(By.linkText("Buy It Now")).
            click();
    }
    
    public SearchItemWidget getSearchResultOnPage(int index)
    {
        List<WebElement> results = resultsList().
                findElements(By.cssSelector(resultListItemCss));
        
        return new SearchItemWidget(results.get(index));
    }
}
