package utils;

import core.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionUtils {

    private static final Logger logger = LoggerFactory.getLogger(ActionUtils.class);
    private static final int MAX_RETRIES = 3;
    private static final long RETRY_DELAY_MS = 500; // Delay between retries in milliseconds

    /**
     * Clicks on an element using standard Selenium click or falls back to JS click if needed.
     *
     * @param locator Locator for the element to click
     */
    public static void click(By locator) {
        int retries = 0;

        while (retries < MAX_RETRIES) {
            try {
                // Handle blocking UI first
                WaitUtils.handleAgeGateIfPresent();
                WaitUtils.waitForOverlayToDisappear();

                WebElement element = WaitUtils.waitForElementClickable(locator);
                scrollToElement(element);

                element.click();
                logger.info("Successfully clicked element with locator: {}", locator);
                return;

            } catch (ElementClickInterceptedException e) {
                // Fallback: JS click
                logger.warn("Standard click failed. Falling back to JS click for locator: {}", locator);
                try {
                    WebElement element = WaitUtils.waitForElementPresence(locator);
                    scrollToElement(element);

                    executeJavaScript("arguments[0].click();", element);
                    logger.info("JS click successful for locator: {}", locator);
                    return;

                } catch (Exception ex) {
                    retries++;
                    logRetryAttempt(retries, locator, ex);
                }

            } catch (StaleElementReferenceException e) {
                retries++;
                logRetryAttempt(retries, locator, e);
            }
        }
    }

    /**
     * Types text into an input field.
     *
     * @param locator Locator for the input field
     * @param text    Text to type
     */
    public static void type(By locator, String text) {
        int retries = 0;

        while (retries < MAX_RETRIES) {
            try {
                WaitUtils.handleAgeGateIfPresent();

                WebElement element = WaitUtils.waitForElementVisible(locator);
                scrollToElement(element);

                element.clear();
                element.sendKeys(text);
                logger.info("Successfully typed text '{}' into element with locator: {}", text, locator);
                return;

            } catch (StaleElementReferenceException e) {
                retries++;
                logRetryAttempt(retries, locator, e);
            }
        }
    }

    /**
     * Retrieves the visible text of an element.
     *
     * @param locator Locator for the element
     * @return Visible text of the element
     */
    public static String getText(By locator) {
        WebElement element = WaitUtils.waitForElementVisible(locator);
        String text = element.getText().trim();
        logger.info("Retrieved text '{}' from element with locator: {}", text, locator);
        return text;
    }

    /**
     * Scrolls to an element using JavaScript.
     *
     * @param element Element to scroll to
     */
    public static void scrollToElement(WebElement element) {
        executeJavaScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
        logger.info("Scrolled to element: {}", element);
    }

    /**
     * Hovers over an element using Actions.
     *
     * @param locator Locator for the element to hover
     */
    public static void hover(By locator) {
        WebElement element = WaitUtils.waitForElementVisible(locator);
        new Actions(DriverManager.getDriver()).moveToElement(element).perform();
        logger.info("Hovered over element with locator: {}", locator);
    }

    /**
     * Checks if an element is displayed.
     *
     * @param locator Locator for the element
     * @return True if the element is displayed, false otherwise
     */
    public static boolean isDisplayed(By locator) {
        try {
            boolean isDisplayed = WaitUtils.waitForElementVisible(locator).isDisplayed();
            logger.info("Element with locator {} is displayed: {}", locator, isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.warn("Element with locator {} is not displayed or not found", locator);
            return false;
        }
    }

    /**
     * Executes JavaScript on the current page.
     *
     * @param script JavaScript code to execute
     * @param args   Arguments to pass to the script
     */
    private static void executeJavaScript(String script, Object... args) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript(script, args);
    }

    /**
     * Logs retry attempts for better visibility.
     *
     * @param attempt Current retry attempt
     * @param locator Locator for the element
     * @param e       Exception that caused the retry
     */
    private static void logRetryAttempt(int attempt, By locator, Exception e) {
        logger.warn("Retry attempt {} failed for locator {}: {}", attempt, locator, e.getMessage());
        if (attempt == MAX_RETRIES) {
            logger.error("Max retries reached for locator {}. Exception: {}", locator, e.getMessage(), e);
            throw new RuntimeException("Max retries reached for locator " + locator, e);
        }
        sleep(RETRY_DELAY_MS);
    }

    /**
     * Pauses execution for a specified duration.
     *
     * @param millis Duration to sleep in milliseconds
     */
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Thread interrupted during sleep: {}", e.getMessage(), e);
        }
    }
}