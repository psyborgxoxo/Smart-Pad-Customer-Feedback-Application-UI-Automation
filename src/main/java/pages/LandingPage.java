package pages;

import org.openqa.selenium.By;
import utils.WaitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LandingPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LandingPage.class);

    // Locators
    private static final By APP_HEADER = By.xpath("//*[contains(.,'kristalball')]");
    private static final By GET_STARTED_BUTTON = By.xpath("//div[@role='button' and text()='Get started']");
    private static final By GIN_ELEMENT = By.xpath("//div[text()='Gin']");

    /**
     * Navigates to the specified URL.
     *
     * @param url The URL to navigate to
     */
    public void navigateTo(String url) {
        logger.info("Navigating to URL: {}", url);
        driver().get(url);
    }

    /**
     * Checks if the app header is visible.
     *
     * @return True if the app header is visible, false otherwise
     */
    public boolean isAppHeaderVisible() {
        try {
            boolean isVisible = WaitUtils.waitForElementVisible(APP_HEADER) != null;
            logger.info("App header visibility: {}", isVisible);
            return isVisible;
        } catch (Exception e) {
            logger.warn("Error checking visibility of App header: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Checks if the "Get Started" button is displayed.
     *
     * @return True if the button is displayed, false otherwise
     */
    public boolean isGetStartedButtonDisplayed() {
        try {
            boolean isDisplayed = WaitUtils.waitForElementVisible(GET_STARTED_BUTTON) != null;
            logger.info("Get Started button visibility: {}", isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.warn("Error checking visibility of Get Started button: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Clicks the "Get Started" button.
     */
    public void clickGetStartedButton() {
        try {
            logger.info("Clicking Get Started button...");
            WaitUtils.waitForElementClickable(GET_STARTED_BUTTON).click();
        } catch (Exception e) {
            logger.error("Error clicking Get Started button: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Checks if the "Gin" element is visible.
     *
     * @return True if the element is visible, false otherwise
     */
    public boolean isGinElementVisible() {
        try {
            boolean isVisible = WaitUtils.waitForElementVisible(GIN_ELEMENT) != null;
            logger.info("Gin element visibility: {}", isVisible);
            return isVisible;
        } catch (Exception e) {
            logger.warn("Error checking visibility of Gin element: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Clicks the "Gin" element.
     */
    public void clickGinElement() {
        try {
            logger.info("Clicking Gin element...");
            WaitUtils.waitForElementClickable(GIN_ELEMENT).click();
        } catch (Exception e) {
            logger.error("Error clicking Gin element: {}", e.getMessage(), e);
            throw e;
        }
    }
}