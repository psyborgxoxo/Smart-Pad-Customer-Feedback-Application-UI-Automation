package pages;

import org.openqa.selenium.By;
import utils.WaitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ProductPage.class);

    // Locators
    private static final By FIRST_PRODUCT = By.xpath("(//a[contains(@href,'/auth/product')])[1]");
    private static final By SHARE_FEEDBACK_BUTTON = By.xpath("//p[contains(text(),'Share Feedback')]");

    /**
     * Checks if the first product is visible.
     *
     * @return True if the first product is visible, false otherwise
     */
    public boolean isFirstProductVisible() {
        try {
            boolean isVisible = WaitUtils.waitForElementVisible(FIRST_PRODUCT) != null;
            logger.info("First product visibility: {}", isVisible);
            return isVisible;
        } catch (Exception e) {
            logger.warn("Error checking visibility of first product: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Clicks the first product.
     */
    public void clickFirstProduct() {
        try {
            logger.info("Clicking first product...");
            WaitUtils.waitForElementClickable(FIRST_PRODUCT).click();
        } catch (Exception e) {
            logger.error("Error clicking first product: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Checks if the "Share Feedback" button is visible.
     *
     * @return True if the button is visible, false otherwise
     */
    public boolean isShareFeedbackVisible() {
        try {
            boolean isVisible = WaitUtils.waitForElementVisible(SHARE_FEEDBACK_BUTTON) != null;
            logger.info("Share Feedback button visibility: {}", isVisible);
            return isVisible;
        } catch (Exception e) {
            logger.warn("Error checking visibility of Share Feedback button: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Clicks the "Share Feedback" button.
     */
    public void clickShareFeedback() {
        try {
            logger.info("Clicking Share Feedback button...");
            WaitUtils.waitForElementClickable(SHARE_FEEDBACK_BUTTON).click();
        } catch (Exception e) {
            logger.error("Error clicking Share Feedback button: {}", e.getMessage(), e);
            throw e;
        }
    }
}