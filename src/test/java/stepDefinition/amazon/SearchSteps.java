package stepDefinition.amazon;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObject.ApplicationInstance;

public class SearchSteps extends ApplicationInstance {
    @Given("^I am in Amazon web site$")
    public void i_am_in_amazon_web_site() throws Throwable {
        amazon.goTo();
        Assert.assertTrue(amazon.getHomePage().isAmazonHomePageDisplayed(), "Amazon home page is not displayed");
    }

    @When("^I search \"([^\"]*)\" in amazon$")
    public void i_search_something_in_amazon(String product) throws Throwable {
        amazon.getHomePage().searchProduct(product);
    }

    @Then("^the product section is displayed$")
    public void the_product_section_is_displayed() throws Throwable {
        Assert.assertTrue(amazon.getProductPage().isProductSectionDisplayed(), "Product section is not displayed");
    }

    @And("^I can see the number of products displayed$")
    public void i_can_see_the_number_of_products_displayed() throws Throwable {
        System.out.println(amazon.getProductPage().numberProducts());
    }
}
