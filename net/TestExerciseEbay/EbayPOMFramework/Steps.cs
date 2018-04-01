using System;
using System.Collections.Generic;

namespace TestExercise.EbayPOMFramework
{
    public class Steps
    {
        private readonly AutomationFramework af;

        public Steps(AutomationFramework af)
        {
            if (af == null)
                throw new ArgumentNullException("af cannot be null");
            this.af = af;
        }

        public void GoToEbayWebSite()
        {
            af.Driver.Navigate().GoToUrl(af.BaseUri);
        }

        public void SearchFor(String searchQuery)
        {
            BasePage page = new BasePage(af);
            page.EnterSearchQuery(searchQuery);
            page.SubmitSearch();
        }

        public void FilterOnlyBuyItNowItems()
        {
            SearchResultsPage page = new SearchResultsPage(af);
            page.ViewOnlyBuyItNowItems();
        }

        public void OpenFirstSearchResult()
        {
            SearchResultsPage page = new SearchResultsPage(af);
            SearchItemWidget widget = page.GetSearchResultOnPage(0);
            widget.OpenItem();
        }

        public ItemDetails GetDetailsFromItemPage()
        {
            ItemPage page = new ItemPage(af);

            return new ItemDetails(
                    page.ItemTitle.Trim(),
                    page.PageUri,
                    page.ItemPrice.Trim());
        }

        public void AddItemToCart()
        {
            ItemPage page = new ItemPage(af);
            page.AddItemtoCart();
        }

        public IList<ItemDetails> GetItemsInCart()
        {
            ShoppingCartPage page = new ShoppingCartPage(af);

            return page.GetItemsInCart();
        }
    }
}
