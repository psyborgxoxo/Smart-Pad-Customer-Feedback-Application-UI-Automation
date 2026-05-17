package utils;

import config.ConfigurationManager;
import core.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class WaitUtils {

    private static WebDriverWait getWait() {
        int timeout = ConfigurationManager.getTimeout();
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
    }

    private static Wait<WebDriver> getFluentWait() {
        int timeout = ConfigurationManager.getTimeout();
        return new FluentWait<>(DriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public static WebElement waitForElementVisible(By locator) {
        return getFluentWait().until(driver -> ExpectedConditions.visibilityOfElementLocated(locator).apply(driver));
    }

    public static WebElement waitForElementClickable(By locator) {
        return getFluentWait().until(driver -> ExpectedConditions.elementToBeClickable(locator).apply(driver));
    }

    public static WebElement waitForElementPresence(By locator) {
        return getFluentWait().until(driver -> ExpectedConditions.presenceOfElementLocated(locator).apply(driver));
    }

    public static List<WebElement> waitForElementsVisible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static boolean waitForElementInvisible(By locator) {
        return getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static boolean waitForTextToBePresentInElement(By locator, String text) {
        return getWait().until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public static void waitForPageLoad() {
        getWait().until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState")
                .equals("complete"));
    }

    public static boolean waitForUrlContains(String partialUrl) {
        return getWait().until(ExpectedConditions.urlContains(partialUrl));
    }

    public static void handleAgeGateIfPresent() {
        By ageBtn = By.xpath("//button[contains(text(),'Yes')]");

        try {
            if (DriverManager.getDriver().findElements(ageBtn).size() > 0) {
                waitForElementClickable(ageBtn).click();
            }
        } catch (Exception ignored) {
        }
    }

    public static void waitForOverlayToDisappear() {
        try {
            By overlay = By.xpath("//div[contains(@class,'fixed') and contains(@style,'rgba')]");
            getWait().until(ExpectedConditions.invisibilityOfElementLocated(overlay));
        } catch (Exception ignored) {
        }
    }
}