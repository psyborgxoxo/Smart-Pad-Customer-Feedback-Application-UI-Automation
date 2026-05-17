package utils;

import core.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class ActionUtils {

    private static final int MAX_RETRIES = 3;

    public static void click(By locator) {
        int retries = 0;

        while (retries < MAX_RETRIES) {
            try {
                // ✅ Handle blocking UI first
                WaitUtils.handleAgeGateIfPresent();
                WaitUtils.waitForOverlayToDisappear();

                WebElement element = WaitUtils.waitForElementClickable(locator);
                scrollToElement(element);

                element.click();
                return;

            } catch (ElementClickInterceptedException e) {

                // 🔁 Fallback: JS click
                try {
                    WebElement element = WaitUtils.waitForElementPresence(locator);
                    scrollToElement(element);

                    JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
                    js.executeScript("arguments[0].click();", element);
                    return;

                } catch (Exception ex) {
                    retries++;
                    if (retries == MAX_RETRIES)
                        throw ex;
                }

            } catch (StaleElementReferenceException e) {
                retries++;
                if (retries == MAX_RETRIES)
                    throw e;
            }
        }
    }

    public static void type(By locator, String text) {
        int retries = 0;

        while (retries < MAX_RETRIES) {
            try {
                WaitUtils.handleAgeGateIfPresent();

                WebElement element = WaitUtils.waitForElementVisible(locator);
                scrollToElement(element);

                element.clear();
                element.sendKeys(text);
                return;

            } catch (StaleElementReferenceException e) {
                retries++;
                if (retries == MAX_RETRIES)
                    throw e;
            }
        }
    }

    public static String getText(By locator) {
        return WaitUtils.waitForElementVisible(locator).getText().trim();
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});",
                element);
    }

    public static void hover(By locator) {
        WebElement element = WaitUtils.waitForElementVisible(locator);
        new Actions(DriverManager.getDriver()).moveToElement(element).perform();
    }

    public static boolean isDisplayed(By locator) {
        try {
            return WaitUtils.waitForElementVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}