package com.testexercise.ebayframework;

import org.openqa.selenium.*;

public class CartItemWidget extends BaseWidget
{
    public CartItemWidget(WebElement elem)
    {
        super(elem);
    }
    
    private final String cartItemLinkCss = "div.fl.infocolcart140 a";
    private final String cartItemPriceCss = "div.fw-b";
    
    public ItemDetails getItemDetails()
    {
        WebElement link = boundingElem.
                findElement(By.cssSelector(cartItemLinkCss));
        
        WebElement price = boundingElem.
                findElement(By.cssSelector(cartItemPriceCss));
        
        return new ItemDetails(
                link.getText().trim(),
                link.getAttribute("href"),
                price.getText());
    }
}
