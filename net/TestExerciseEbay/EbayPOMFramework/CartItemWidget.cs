using System;
using OpenQA.Selenium;

namespace TestExercise.EbayPOMFramework
{
    public class CartItemWidget : BaseWidget
    {
        public CartItemWidget(IWebElement elem) : base(elem) { }


        private const string cartItemLinkCss = "div.fl.infocolcart140 a";
        private const string cartItemPriceCss = "div.fw-b";

        public ItemDetails GetItemDetails()
        {
            IWebElement link = boundingElem.
                    FindElement(By.CssSelector(cartItemLinkCss));

            IWebElement price = boundingElem.
                    FindElement(By.CssSelector(cartItemPriceCss));

            return new ItemDetails(
                    link.Text.Trim(),
                    link.GetAttribute("href"),
                    price.Text);
        }
    }
}