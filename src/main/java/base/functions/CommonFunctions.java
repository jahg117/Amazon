package base.functions;

import base.driverInitialize.DriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FileReading;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonFunctions {

    private WebDriver driver = DriverFactory.getDriver();

    protected FileReading fileReading = new FileReading();

    private Logger logger = Logger.getLogger(CommonFunctions.class);

    public CommonFunctions() {
        fileReading.setLog4jFile();
    }

    protected WebElement getWebElement(By locator) {
        return driver.findElement(locator);
    }

    protected List<WebElement> getWebElementList(By locator) {
        return driver.findElements(locator);
    }

    protected boolean waitForElementClickable(WebElement element, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            logger.info("Element found "+getWebElementLocatorPath(element));
            return true;
        }catch (Exception e){
            logger.warn("Element was not found "+getWebElementLocatorPath(element));
            return false;
        }
    }

    protected boolean waitForElementVisibility(WebElement webElement, int timeOutInSeconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            logger.info("Element found: " + getWebElementLocatorPath(webElement));
            return true;
        } catch (Exception e) {
            logger.warn("Element was not found: " + getWebElementLocatorPath(webElement));
            return false;
        }
    }

    protected boolean waitForElementListVisible(List<WebElement> elements, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
            logger.info("List of web elements is visible "+getWebElementLocatorPath(elements));
            return true;
        }catch (Exception e){
            logger.warn("List of web elements is not visible");
            return false;
        }
    }

    protected void waitForPageToLoad() {
        WebDriverWait wait= new WebDriverWait(driver, 30);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jsLoad = webDriver ->  ((JavascriptExecutor)webDriver)
                .executeScript("return document.readyState").toString().equals("complete");

        boolean jsReady = jsExecutor.executeScript("return document.readyState").toString().equals("complete");

        if(!jsReady) {
            wait.until(jsLoad);
        }else {
            logger.info("Page is ready !");
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        }
    }
    protected boolean waitForElementPresenceBy(By locator, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            logger.info("Element found: " + locator.toString());
            return true;
        }catch (Exception e){
            logger.warn("Element was not found: " + locator.toString());
            return false;
        }
    }
    protected boolean waitForElementToBeClickableBy(By locator, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            logger.info("Element found: " + locator.toString());
            return true;
        }catch (Exception e){
            logger.info("Element not found: " + locator.toString());
            return false;
        }
    }
    protected boolean waitForElementVisibilityOfElementLocatedBy(By locator, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Element found: " + locator.toString());
            return true;
        }catch (Exception e){
            logger.info("Element not found: " + locator.toString());
            return false;
        }
    }
    protected boolean waitForNumberOfElementsToBe(By locator, int numberElements,int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.numberOfElementsToBe(locator, numberElements));
            logger.info("Elements found: " + locator.toString()+ " number of elements: "+numberElements);
            return true;
        }catch (Exception e){
            logger.warn("Expected number of elements not found");
            return false;
        }
    }
    protected boolean waitForNumberOfElementsToBeLessThanBy(By locator, int numberElements,int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(locator, numberElements));
            logger.info("Elements found: " + locator.toString()+ " number of elements less than: "+numberElements);
            return true;
        }catch (Exception e){
            logger.warn("Expected number of elements not found");
            return false;
        }
    }
    protected boolean waitForNumberOfElementsToBeMoreThanBy(By locator, int numberElements,int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, numberElements));
            logger.info("Elements found: " + locator.toString()+ " number of elements more than: "+numberElements);
            return true;
        }catch (Exception e){
            logger.warn("Expected number of elements not found");
            return false;
        }
    }
    protected boolean waitForPresenceOfAllElementsLocatedBy(By locator, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            logger.info("Elements found: " + locator.toString());
            return true;
        }catch (Exception e){
            logger.info("Elements not found: " + locator.toString());
            return false;
        }
    }
    protected void clickElementClickable(By webElement, int waitTime) throws Exception {
        if(waitForElementToBeClickableBy(webElement, waitTime)){
            clickWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element is not clickable");
            throw new NoSuchElementException("Element not clickable");
        }
    }

    protected void clickElementVisible(By webElement, int waitTime) throws Exception {
        if(waitForElementPresenceBy(webElement, waitTime)){
            clickWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    protected void clickAndMoveToElementVisible(By webElement, int waitTime) throws Exception {
        if(waitForElementPresenceBy(webElement, waitTime)){
            clickAndMoveToWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    protected void clickAndMoveToElementClickable(By webElement, int waitTime) throws Exception {
        if(waitForElementToBeClickableBy(webElement, waitTime)){
            clickAndMoveToWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element is not clickable");
            throw new NoSuchElementException("Element not clickable");
        }
    }
    protected void clickElementClickable(WebElement webElement, int waitTime) throws Exception {
        if(waitForElementClickable(webElement, waitTime)){
            clickWebElementByActions(webElement);
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element is not clickable");
            throw new NoSuchElementException("Element not clickable");
        }
    }

    protected void clickElementVisible(WebElement webElement, int waitTime) throws Exception {
        if(waitForElementVisibility(webElement, waitTime)){
            clickWebElementByActions(webElement);
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    protected void clickAndMoveToElementClickable(WebElement webElement, int waitTime) throws Exception {
        if(waitForElementClickable(webElement, waitTime)){
            clickAndMoveToWebElementByActions(webElement);
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element is not clickable");
            throw new NoSuchElementException("Element not clickable");
        }
    }

    protected void scrollToWebElementJS(WebElement webElement) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
        } catch (Exception e) {
            logger.error("Web Element not found or invalid");
            throw new NoSuchElementException("Web Element not found or invalid");
        }
    }
    protected void sendKeysAndMoveToElementVisible(By webElement, String text, int waitTime) throws Exception {
        if(waitForElementPresenceBy(webElement, waitTime)){
            sendKeysAndMoveToWebElementByActions(getWebElement(webElement), text);
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }
    protected void sendKeysAndMoveToElementClickable(By webElement, String text, int waitTime) throws Exception {
        if(waitForElementToBeClickableBy(webElement, waitTime)){
            sendKeysAndMoveToWebElementByActions(getWebElement(webElement), text);
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }

    protected void sendKeysElementVisible(By webElement, String text, int waitTime) throws Exception {
        if(waitForElementPresenceBy(webElement, waitTime)){
            sendKeysWebElementByActions(getWebElement(webElement), text);
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }
    protected void sendKeysElementClickable(By webElement, String text, int waitTime) throws Exception {
        if(waitForElementToBeClickableBy(webElement, waitTime)){
            sendKeysWebElementByActions(getWebElement(webElement), text);
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }
    protected void sendKeysAndMoveToElementVisible(WebElement webElement, String text, int waitTime) throws Exception {
        if(waitForElementVisibility(webElement, waitTime)){
            sendKeysAndMoveToWebElementByActions(webElement, text);
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }
    protected void sendKeysAndMoveToElementClickable(WebElement webElement, String text, int waitTime) throws Exception {
        if(waitForElementClickable(webElement, waitTime)){
            sendKeysAndMoveToWebElementByActions(webElement, text);
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }
    protected void sendKeysElementVisible(WebElement webElement, String text, int waitTime) throws Exception {
        if(waitForElementVisibility(webElement, waitTime)){
            sendKeysWebElementByActions(webElement, text);
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }
    protected void sendKeysElementClickable(WebElement webElement, String text, int waitTime) throws Exception {
        if(waitForElementClickable(webElement, waitTime)){
            sendKeysWebElementByActions(webElement, text);
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }

    protected <f> void switchToFrameByWebElementIndexOrName(f frame, int timeSeconds){
        try{
            String frameType = frame.getClass().getName();
            if(frameType.contains("Integer")) {
                driver.switchTo().frame((Integer) frame);
            }else{
                if(frameType.contains("String")) {
                    driver.switchTo().frame(frame.toString());
                }else{
                    if(waitForElementVisibility((WebElement) frame, timeSeconds)){
                        driver.switchTo().frame((WebElement) frame);
                    }else{
                        logger.error("The Web Element was not found");
                        throw new NoSuchElementException("Element not valid");
                    }
                }
            }
        }catch(Exception e){
            logger.error(e);
        }
    }
    protected void sendKeysWebElementByActions(WebElement wElement, String text) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.click(wElement).sendKeys(wElement, text).build().perform();
            logger.info("Element found: "+getWebElementLocatorPath(wElement));
            logger.info("Keys sent: "+text);
        } catch (Exception e) {
            logger.error("Element not visible or not clickable: "+getWebElementLocatorPath(wElement));
            logger.error(e.getMessage());
        }
    }
    protected void sendKeysByActions(String text) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.sendKeys(text).build().perform();
            logger.info("Keys sent: "+text);
        } catch (Exception e) {
            logger.error("Element not visible or not clickable");
            logger.error(e.getMessage());
        }
    }
    protected void sendKeysAndMoveToWebElementByActions(WebElement wElement, String text) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(wElement).sendKeys(wElement, text).build().perform();
            logger.info("Element found: "+getWebElementLocatorPath(wElement));
            logger.info("Keys sent: "+text);
        } catch (Exception e) {
            logger.error("Element not visible or not clickable: "+getWebElementLocatorPath(wElement));
            logger.error(e.getMessage());
        }
    }

    private void clickAndMoveToWebElementByActions(WebElement wElement) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(wElement).click(wElement).build().perform();
            logger.info("Element found: "+getWebElementLocatorPath(wElement));
        } catch (Exception e) {
            logger.error("Element not visible or not clickable: "+getWebElementLocatorPath(wElement));
            logger.error(e.getMessage());
        }
    }

    private void clickWebElementByActions(WebElement wElement) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.click(wElement).build().perform();
            logger.info("Element found: "+getWebElementLocatorPath(wElement));
        } catch (Exception e) {
            logger.error("Element not visible or not clickable: "+getWebElementLocatorPath(wElement));
            logger.error(e.getMessage());
        }
    }

    private String getWebElementLocatorPath(WebElement webElement){
        try{
            return webElement.toString().split("-> ")[1].replace("]","");
        }catch(Exception e){
            return webElement.toString().split("DefaultElementLocator")[1].replace("'","");
        }
    }
    private String getWebElementLocatorPath(List<WebElement> webElement){
        try{
            return webElement.toString().split("-> ")[1].replace("]","");
        }catch(Exception e){
            return webElement.toString().split("DefaultElementLocator")[1].replace("'","");
        }
    }
}