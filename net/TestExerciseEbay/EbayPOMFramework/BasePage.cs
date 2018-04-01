using System;
using OpenQA.Selenium;

namespace TestExercise.EbayPOMFramework
{ 
    public class BasePage
    {
        protected readonly AutomationFramework af;

        public BasePage(AutomationFramework af)
        {
            this.af = af;
        }

        const string searchTextInputCss = "form#gh-f input[type='text']";
        const string searchSubmitCss = "form#gh-f input[type='submit']";

        public IWebElement SearchBox()
        {
            return af.Driver.FindElement(By.CssSelector(searchTextInputCss));
        }

        public IWebElement SearchSubmit()
        {
            return af.Driver.FindElement(By.CssSelector(searchSubmitCss));
        }

        public void EnterSearchQuery(String searchQuery)
        {
            SearchBox().Clear();
            SearchBox().SendKeys(searchQuery);
        }

        public void SubmitSearch()
        {
            SearchSubmit().Click();
        }

        public string PageUri => af.Driver.Url;
    }
}