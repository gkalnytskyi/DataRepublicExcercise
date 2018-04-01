using System;
using OpenQA.Selenium;

namespace TestExercise.EbayPOMFramework
{
    public class SearchItemWidget : BaseWidget
    {
        public SearchItemWidget(IWebElement elem) : base(elem) { }

        private const string itemLinkCss = ".lvtitle > a";

        public void OpenItem()
        {
            boundingElem.FindElement(By.CssSelector(itemLinkCss)).Click();
        }
    }
}