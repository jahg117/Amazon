package pageObject.pages.Amazon;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends CommonFunctions {
    @FindBy(css = "#twotabsearchtextbox")
    private WebElement input_searchInput;

    @FindBy(css = "input[id*=submit-button]")
    private WebElement button_searchProduct;

    public boolean isAmazonHomePageDisplayed(){
        return waitForElementVisibility(input_searchInput, 30);
    }

    public void searchProduct(String product) throws Exception {
        sendKeysElementVisible(input_searchInput, product, 10);
        clickElementClickable(button_searchProduct, 10);
    }
}
