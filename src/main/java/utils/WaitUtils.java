package utils;

import config.ConfigurationManager;
import core.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class WaitUtils {

    private static final Logger logger = LoggerFactory.getLogger(WaitUtils.class);

    // Constants for common locators
    private static final By AGE_GATE_BUTTON = By.xpath("//button[contains(text(),'Yes')]");
    private static final By OVERLAY_LOCATOR = By.xpath("//div[contains(@class,'fixed') and contains(@style,'rgba')]");

    /**
     * Retrieves a WebDriverWait instance with the configured timeout.
     *
     * @return WebDriverWait instance
     */
    private static WebDriverWait getWait() {
        int timeout = ConfigurationManager.getTimeout();
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
    }

    /**
     * Retrieves a FluentWait instance with the configured timeout and polling interval.
     *
     * @return FluentWait instance
     */
    private static Wait<WebDriver> getFluentWait() {
        int timeout = ConfigurationManager.getTimeout();
        return new FluentWait<>(DriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    /**
     * Waits for an element to be visible.
     *
     * @param locator Locator for the element
     * @return WebElement that is visible
     */
    public static WebElement waitForElementVisible(By locator) {
        try {
            WebElement element = getFluentWait().until(driver -> ExpectedConditions.visibilityOfElementLocated(locator).apply(driver));
            logger.info("Element with locator {} became visible", locator);
            return element;
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for element with locator {} to become visible", locator, e);
            throw e;
        }
    }

    /**
     * Waits for an element to be clickable.
     *
     * @param locator Locator for the element
     * @return WebElement that is clickable
     */
    public static WebElement waitForElementClickable(By locator) {
        try {
            WebElement element = getFluentWait().until(driver -> ExpectedConditions.elementToBeClickable(locator).apply(driver));
            logger.info("Element with locator {} became clickable", locator);
            return element;
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for element with locator {} to become clickable", locator, e);
            throw e;
        }
    }

    /**
     * Waits for an element to be present in the DOM.
     *
     * @param locator Locator for the element
     * @return WebElement that is present
     */
    public static WebElement waitForElementPresence(By locator) {
        try {
            WebElement element = getFluentWait().until(driver -> ExpectedConditions.presenceOfElementLocated(locator).apply(driver));
            logger.info("Element with locator {} became present in the DOM", locator);
            return element;
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for element with locator {} to be present in the DOM", locator, e);
            throw e;
        }
    }

    /**
     * Waits for multiple elements to be visible.
     *
     * @param locator Locator for the elements
     * @return List of WebElements that are visible
     */
    public static List<WebElement> waitForElementsVisible(By locator) {
        try {
            List<WebElement> elements = getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            logger.info("Elements with locator {} became visible", locator);
            return elements;
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for elements with locator {} to become visible", locator, e);
            throw e;
        }
    }

    /**
     * Waits for an element to become invisible.
     *
     * @param locator Locator for the element
     * @return True if the element becomes invisible, false otherwise
     */
    public static boolean waitForElementInvisible(By locator) {
        try {
            boolean result = getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
            logger.info("Element with locator {} became invisible", locator);
            return result;
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for element with locator {} to become invisible", locator, e);
            throw e;
        }
    }

    /**
     * Waits for text to be present in an element.
     *
     * @param locator Locator for the element
     * @param text    Text to wait for
     * @return True if the text is present, false otherwise
     */
    public static boolean waitForTextToBePresentInElement(By locator, String text) {
        try {
            boolean result = getWait().until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
            logger.info("Text '{}' became present in element with locator {}", text, locator);
            return result;
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for text '{}' to be present in element with locator {}", text, locator, e);
            throw e;
        }
    }

    /**
     * Waits for the page to fully load.
     */
    public static void waitForPageLoad() {
        try {
            getWait().until(driver -> ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState")
                    .equals("complete"));
            logger.info("Page load completed");
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for page to load", e);
            throw e;
        }
    }

    /**
     * Waits for the URL to contain a specific substring.
     *
     * @param partialUrl Substring to wait for in the URL
     * @return True if the URL contains the substring, false otherwise
     */
    public static boolean waitForUrlContains(String partialUrl) {
        try {
            boolean result = getWait().until(ExpectedConditions.urlContains(partialUrl));
            logger.info("URL contains '{}'", partialUrl);
            return result;
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for URL to contain '{}'", partialUrl, e);
            throw e;
        }
    }

    /**
     * Handles age gate popups if present.
     */
    public static void handleAgeGateIfPresent() {
        try {
            if (!DriverManager.getDriver().findElements(AGE_GATE_BUTTON).isEmpty()) {
                WebElement ageButton = waitForElementClickable(AGE_GATE_BUTTON);
                ageButton.click();
                logger.info("Clicked age gate button");
            }
        } catch (Exception e) {
            logger.warn("Age gate not present or could not be handled: {}", e.getMessage());
        }
    }

    /**
     * Waits for overlay elements to disappear.
     */
    public static void waitForOverlayToDisappear() {
        try {
            getWait().until(ExpectedConditions.invisibilityOfElementLocated(OVERLAY_LOCATOR));
            logger.info("Overlay disappeared");
        } catch (Exception e) {
            logger.warn("Overlay did not disappear or was not found: {}", e.getMessage());
        }
    }
}