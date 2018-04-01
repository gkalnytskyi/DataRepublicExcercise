using System;
using System.Collections.Generic;
using System.Linq;
using OpenQA.Selenium;

namespace TestExercise.EbayPOMFramework
{
    public class SearchResultsPage : BasePage
    {
        public SearchResultsPage(AutomationFramework af) : base(af) { }

        private const String resultListId = "ListViewInner";
        private const String resultListItemCss = "#ListViewInner > li";
        private const String itemListingTypeCss = "div.pnl-b.frmt";

        public IWebElement resultsList()
        {
            return af.Driver.FindElement(By.Id(resultListId));
        }

        public void ViewOnlyBuyItNowItems()
        {
            af.Driver.
                FindElement(By.CssSelector(itemListingTypeCss)).
                FindElement(By.LinkText("Buy It Now")).
                Click();
        }

        public SearchItemWidget GetSearchResultOnPage(int index)
        {
            IReadOnlyCollection<IWebElement> results = resultsList().
                    FindElements(By.CssSelector(resultListItemCss));

            return new SearchItemWidget(results.ElementAt(index));
        }
    }
}
