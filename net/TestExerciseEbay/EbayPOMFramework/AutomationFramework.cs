using System;
using OpenQA.Selenium;

namespace TestExercise.EbayPOMFramework
{
    public class AutomationFramework
    {
        public string BaseUri { get; }
        public IWebDriver Driver { get; }

        public AutomationFramework(IWebDriver driver, String baseUri)
        {
            if (driver == null)
                throw new ArgumentNullException("driver cannot be null");
            if (baseUri == null)
                throw new ArgumentNullException("baseUri cannot be null");

            this.Driver = driver;
            this.BaseUri = baseUri;
            Init();
        }

        private void Init()
        {
            Driver.Manage().Timeouts().ImplicitWait = new TimeSpan(0, 0, 1);
        }
    }
}