package com.datarepublic.testexercise.ebayframework;

import java.net.*;

public class ItemDetails
{
    private final String title;
    private final String uri;
    private final String price;
    
    public String title() { return title; };
    public String uri() { return uri; };
    public String price() { return price; };
    
    public ItemDetails(
            String title,
            String uri,
            String price)
    {
        this.title = title;
        this.uri = uri;
        this.price = price;
    }
    
    public String itemId() throws URISyntaxException
    {
        URI itemURI = new URI(uri);
        String itemId = itemURI.getPath().toString();
        itemId = itemId.substring(itemId.lastIndexOf("/")+1);
        return itemId;
    }

}
