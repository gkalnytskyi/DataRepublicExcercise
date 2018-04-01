using System;
using OpenQA.Selenium;

namespace TestExercise.EbayPOMFramework
{
    public class BaseWidget
    {
        protected readonly IWebElement boundingElem;
    
    public BaseWidget(IWebElement elem)
        {
            if (elem == null)
                throw new ArgumentNullException("elem cannot be null");
            boundingElem = elem;
        }
    }
}