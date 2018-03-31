package com.testexercise.ebayframework;

import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;

public class WebDriverFactory
{
    public static WebDriver getDriver(
            WebBrowserType browser,
            String driverPath)
    {
        switch (browser)
        {
            case CHROME:
                return getChromeDriver(driverPath);
            case FIREFOX:
                return getFirefoxDriver(driverPath);
            default:
                throw new IllegalArgumentException("Unknown browser type");
        }
    }
    
    public static WebDriver getChromeDriver(String path)
    {
        System.setProperty("webdriver.chrome.driver", path);
       
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized", "disable-extensions");
        
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("spellcheck.dictionary", "");
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        
        options.setExperimentalOption("prefs", prefs);
        
        return new ChromeDriver(options);
    }
    
    public static WebDriver getFirefoxDriver(String path)
    {
        System.setProperty("webdriver.gecko.driver", path);
        
        return new FirefoxDriver();
    }
}
