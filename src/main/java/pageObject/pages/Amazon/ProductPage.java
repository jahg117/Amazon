package pageObject.pages.Amazon;

import base.functions.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends CommonFunctions {
    @FindBy(css = "div.s-main-slot.s-search-results")
    private WebElement form_results;

    private By list_products = By.cssSelector("div[data-cel-widget*='search_result'][data-component-type='s-search-result']");

    public boolean isProductSectionDisplayed(){
        return waitForElementVisibility(form_results, 30);
    }

    public int numberProducts(){
        waitForPresenceOfAllElementsLocatedBy(list_products, 30);
        return getWebElementList(list_products).size();
    }
}
