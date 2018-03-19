package com.datarepublic.testexercise.ebayframework;

import org.openqa.selenium.WebElement;

public class BaseWidget
{
    protected final WebElement boundingElem;
    
    public BaseWidget(WebElement elem)
    {
        if (elem == null)
            throw new IllegalArgumentException("elem cannot be null");
        boundingElem = elem;
    }
}
