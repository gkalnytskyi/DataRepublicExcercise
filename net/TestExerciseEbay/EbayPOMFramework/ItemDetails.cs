using System;

namespace TestExercise.EbayPOMFramework
{
    public class ItemDetails
    {

        public string Title { get; }
        public string Uri { get; }
        public string Price { get; }

        public ItemDetails(
            String title,
            String uri,
            String price)
        {
            this.Title = title;
            this.Uri = uri;
            this.Price = price;
        }

        public string ItemId
        {
            get
            {
                Uri itemURI = new Uri(Uri);
                string itemId = itemURI.AbsolutePath;
                itemId = itemId.Substring(itemId.LastIndexOf("/")+1);
                return itemId;
            }
        }
    }
}