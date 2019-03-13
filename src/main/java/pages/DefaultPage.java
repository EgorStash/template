package pages;

import org.apache.xpath.operations.Bool;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Screenshoter;
import utils.WebDriverCreator;

public class DefaultPage {

    private static final int DEFAULT_TIMEOUT = 30;
    protected WebDriver driver;
    protected Actions action;
    protected JavascriptExecutor jsExecutor;

    public DefaultPage() {
        driver = WebDriverCreator.getDriver();
        action = new Actions(driver);
        jsExecutor = ((JavascriptExecutor) driver);
    }

    protected void click(By locator){
        WebElement element = driver.findElement(locator);
        hilightElement(locator);
        Screenshoter.makeScreen();
        unHilightElement(locator);
        action.moveToElement(element).click().build().perform();
    }

    protected void type(By locator, String text){
        WebElement element = driver.findElement(locator);
        hilightElement(locator);
        element.clear();
        element.sendKeys(text);
        Screenshoter.makeScreen();
        unHilightElement(locator);
    }

    protected boolean isElementPresent(By locator){
        return !driver.findElements(locator).isEmpty();
//        if(driver.findElements(locator).size() > 0){
//            return true;
//        }
//        return false;
    }

    protected void waitForElementPresent(By locator){
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected void waitForElementVisible(By locator) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected void waitForElementEnabled(By locator) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void hilightElement(By locator){
        jsExecutor.executeScript("arguments[0].style.border='5px solid green'", driver.findElement(locator));
    }

    protected void unHilightElement(By locator){
        jsExecutor.executeScript("arguments[0].style.border='0px'", driver.findElement(locator));
    }

    private ExpectedCondition<Boolean> isAjaxFinished(){
        return (driver) -> {
            return (Boolean) jsExecutor.executeScript("return JQuery.active == 0");
        };
    }

    protected void waitForAjaxProcessed(){
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(isAjaxFinished());
    }

    protected void switchToNewWindow(){
        for(String window : driver.getWindowHandles()){
            driver.switchTo().window(window);
        }
        driver.manage().window().maximize();
    }

    protected void switchToMainWindow(){
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
    }

}
