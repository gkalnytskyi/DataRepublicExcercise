package com.datarepublic.testexercise.ebayframework;

import java.util.*;

public class Steps
{
    private final AutomationFramework af;
    
    public Steps(AutomationFramework af)
    {
        if (af == null)
            throw new IllegalArgumentException("af cannot be null");
        this.af = af;
    }
    
    public void goToEbayWebSite()
    {
        af.driver().get(af.baseUri());
    }
    
    public void searchFor(String searchQuery)
    {
        BasePage page = new BasePage(af);
        page.enterSearchQuery(searchQuery);
        page.submitSearch();
    }
    
    public void filterOnlyBuyItNowItems()
    {
        SearchResultsPage page = new SearchResultsPage(af);
        page.viewOnlyBuyItNowItems();
    }
    
    public void openFirstSearchResult()
    {
        SearchResultsPage page = new SearchResultsPage(af);
        SearchItemWidget widget = page.getSearchResultOnPage(0);
        widget.openItem();
    }
    
    public ItemDetails getDetailsFromItemPage()
    {
        ItemPage page = new ItemPage(af);
        
        return new ItemDetails(
                page.getItemTitle().trim(),
                page.getPageUri(),
                page.getItemPrice().trim());
    }
    
    public void addItemToCart()
    {
        ItemPage page = new ItemPage(af);
        page.addItemtoCart();
    }
    
    public List<ItemDetails> getItemsInCart()
    {
        ShoppingCartPage page = new ShoppingCartPage(af);
        
        return page.getItemsInCart();
    }
}
