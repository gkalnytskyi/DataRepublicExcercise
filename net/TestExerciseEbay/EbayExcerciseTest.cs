using System;
using System.Linq;

using OpenQA.Selenium;
using TestExercise.EbayPOMFramework;

using NUnit.Framework;

namespace TestExercise
{
    [TestFixture]
    public class EbayExcerciseTest
    {
        IWebDriver driver;
        AutomationFramework af;
        Steps steps;

        // @Parameters({ "browser", "driverpath" })
        [SetUp]
        public void Setup()
        {
            driver = WebDriverFactory.
                GetDriver(
                    (WebBrowserType)Enum.Parse(
                        typeof(WebBrowserType),
                        TestContext.Parameters["browser"].ToUpper()),
                    TestContext.Parameters["driverpath"]);
            af = new AutomationFramework(driver, "https://www.ebay.com.au");
            steps = new Steps(af);
        }

        [TearDown]
        public void TearDown()
        {
            if (driver != null)
            {
                driver.Close();
                try
                {
                    // an issue with geckodriver
                    // https://github.com/mozilla/geckodriver/issues/966
                    driver.Quit();
                }
                catch { }
            }
        }

        [Test]
        public void Search_AddItemsToCart_Validate()
        {
            steps.GoToEbayWebSite();

            steps.SearchFor("Asus GeForce GTX 1070");
            steps.FilterOnlyBuyItNowItems();
            steps.OpenFirstSearchResult();

            ItemDetails item1 = steps.GetDetailsFromItemPage();

            steps.AddItemToCart();

            steps.SearchFor("sewing pins");
            steps.FilterOnlyBuyItNowItems();
            steps.OpenFirstSearchResult();

            ItemDetails item2 = steps.GetDetailsFromItemPage();

            steps.AddItemToCart();

            var itemsInCart = steps.GetItemsInCart();

            string[] itemIds = itemsInCart.Select(x => x.ItemId).ToArray();

            Assert.That(itemIds, Is.EquivalentTo(new[] { item1.ItemId, item2.ItemId }));


            ItemDetails cartItem1 = itemsInCart.First(x => x.ItemId == item1.ItemId);

            ItemDetails cartItem2 = itemsInCart.First(x => x.ItemId == item2.ItemId);

            Assert.That(cartItem1.Title, Is.EqualTo(item1.Title));
            Assert.That(cartItem1.Price, Is.EqualTo(item1.Price));
            Assert.That(cartItem2.Title, Is.EqualTo(item2.Title));
            Assert.That(cartItem2.Price, Is.EqualTo(item2.Price));
        }
    }
}
