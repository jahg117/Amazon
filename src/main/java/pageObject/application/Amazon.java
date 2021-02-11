package pageObject.application;

import base.driverInitialize.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObject.pages.Amazon.HomePage;
import pageObject.pages.Amazon.ProductPage;
import utils.JsonFiles;

import java.nio.file.NoSuchFileException;

public class Amazon {
    private WebDriver driver;

    private HomePage homePage;
    private ProductPage productPage;

    public Amazon(){
        driver = DriverFactory.getDriver();
        homePage = PageFactory.initElements(driver, HomePage.class);
        productPage = PageFactory.initElements(driver, ProductPage.class);
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public ProductPage getProductPage() {
        return productPage;
    }

    public void goTo() throws NoSuchFileException {
        JsonFiles file = new JsonFiles();
        file.setFileName("loginCredentials");
        driver.get(file.getField("url"));
    }
}
