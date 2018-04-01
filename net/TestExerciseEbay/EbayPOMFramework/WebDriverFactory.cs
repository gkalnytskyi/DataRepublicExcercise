using System;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Firefox;

namespace TestExercise.EbayPOMFramework
{
    public class WebDriverFactory
    {
        public static IWebDriver GetDriver(
                WebBrowserType browser,
                String driverPath)
        {
            switch (browser)
            {
                case WebBrowserType.CHROME:
                    return GetChromeDriver(driverPath);
                case WebBrowserType.FIREFOX:
                    return GetFirefoxDriver(driverPath);
                default:
                    throw new ArgumentException("Unknown browser type");
            }
        }

        public static IWebDriver GetChromeDriver(String path)
        {
            ChromeOptions options = new ChromeOptions();

            options.AddArguments("start-maximized", "disable-extensions");

            options.AddLocalStatePreference("profile.default_content_settings.popups", 0);
            options.AddLocalStatePreference("spellcheck.dictionary", "");
            options.AddLocalStatePreference("credentials_enable_service", false);
            options.AddLocalStatePreference("profile.password_manager_enabled", false);

            if (string.IsNullOrWhiteSpace(path))
            {
                return new ChromeDriver(options);
            }
            else
            {
                return new ChromeDriver(path, options);
            }
        }

        public static IWebDriver GetFirefoxDriver(String path)
        {
            if (string.IsNullOrWhiteSpace(path))
            {
                return new FirefoxDriver();
            }
            else
            {
                return new FirefoxDriver(path);
            }
        }
    }
}
