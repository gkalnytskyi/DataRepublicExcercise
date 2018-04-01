using System;
using System.Collections.Generic;
using OpenQA.Selenium;

namespace TestExercise.EbayPOMFramework
{
    public class ShoppingCartPage : BasePage
    {
        public ShoppingCartPage(AutomationFramework af) : base(af) { }

        private const String shoppingCartListId = "ShopCart";

        public IWebElement ShoppingCartList
        {

            get { return af.Driver.FindElement(By.Id(shoppingCartListId)); }
        }

        private const string cartItemInfoCss = "div.fr.itemInfoColcart140";

        public IList<ItemDetails> GetItemsInCart()
        {
            IReadOnlyCollection<IWebElement> cartItems =
                    ShoppingCartList.FindElements(By.CssSelector(cartItemInfoCss));

            List<ItemDetails> details =
                    new List<ItemDetails>(cartItems.Count);

            foreach (IWebElement cartItem in cartItems)
            {
                details.Add(new CartItemWidget(cartItem).GetItemDetails());
            }

            return details;
        }
    }
}
