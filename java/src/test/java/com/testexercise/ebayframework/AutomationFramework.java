package com.testexercise.ebayframework;

import java.net.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;

public class AutomationFramework
{
    private final WebDriver driver;
    private final String baseUri;
    
    public String baseUri()
    {
        return baseUri;
    }
    
    public AutomationFramework(WebDriver driver, String baseUri)
            throws URISyntaxException
    {
        if (driver == null)
            throw new IllegalArgumentException("driver cannot be null");
        if (baseUri == null)
            throw new IllegalArgumentException("baseUri cannot be null");
        
        this.driver = driver;
        this.baseUri = baseUri;
        init();
    }
    
    private void init()
    {
        driver.manage().
            timeouts().
            implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }
    
    public WebDriver driver()
    {
        return driver;
    }
}
