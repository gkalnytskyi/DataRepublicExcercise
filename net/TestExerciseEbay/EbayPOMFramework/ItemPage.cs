using System;
using OpenQA.Selenium;

namespace TestExercise.EbayPOMFramework
{
    public class ItemPage : BasePage
    {

        public ItemPage(AutomationFramework af) : base(af) { }

        private const string addToCartId = "isCartBtn_btn";
        private const string itemTitleId = "itemTitle";
        private const string itemPriceId = "prcIsum";

        public void AddItemtoCart()
        {
            af.Driver.FindElement(By.Id(addToCartId)).Click();
        }

        public String ItemTitle => af.Driver.FindElement(By.Id(itemTitleId)).Text;

        public String ItemPrice => af.Driver.FindElement(By.Id(itemPriceId)).Text;
    }
}
