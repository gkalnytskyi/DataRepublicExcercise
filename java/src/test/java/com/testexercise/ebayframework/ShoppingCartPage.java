package com.testexercise.ebayframework;

import java.util.*;
import org.openqa.selenium.*;

public class ShoppingCartPage extends BasePage
{
    public ShoppingCartPage(AutomationFramework af)
    {
        super(af);
    }
    
    private final String shoppingCartListId = "ShopCart";
    
    public WebElement shoppingCartList()
    {
        return af.driver().findElement(By.id(shoppingCartListId));
    }
    
    private final String cartItemInfoCss = "div.fr.itemInfoColcart140";
    
    public List<ItemDetails> getItemsInCart()
    {
        List<WebElement> cartItems = shoppingCartList().
                findElements(By.cssSelector(cartItemInfoCss));
        
        ArrayList<ItemDetails> details =
                new ArrayList<ItemDetails>(cartItems.size());
        
        for (WebElement cartItem: cartItems)
        {
            details.add(new CartItemWidget(cartItem).getItemDetails());
        }
        
        return details;
    }
}
