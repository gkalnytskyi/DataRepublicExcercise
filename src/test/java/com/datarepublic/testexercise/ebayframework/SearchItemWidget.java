package com.datarepublic.testexercise.ebayframework;

import org.openqa.selenium.*;

public class SearchItemWidget extends BaseWidget
{   
    public SearchItemWidget(WebElement elem)
    {
        super(elem);
    }
    
    private final String itemLinkCss = ".lvtitle > a";
    
    void openItem()
    {
        boundingElem.findElement(By.cssSelector(itemLinkCss)).click();
    }
}
