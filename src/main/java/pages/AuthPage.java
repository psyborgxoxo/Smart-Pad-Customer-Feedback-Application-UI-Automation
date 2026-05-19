package pages;

import org.openqa.selenium.By;
import utils.WaitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(AuthPage.class);

    // Locators
    private static final By CONTINUE_BUTTON = By.xpath("//div[@role='button' and text()='Continue without an account']");
    private static final By AGE_VERIFICATION_BUTTON = By.xpath("//button[contains(text(),'Yes')]");

    /**
     * Checks if the "Continue without an account" button is visible.
     *
     * @return True if the button is visible, false otherwise
     */
    public boolean isContinueButtonVisible() {
        try {
            boolean isVisible = WaitUtils.waitForElementVisible(CONTINUE_BUTTON) != null;
            logger.info("Continue button visibility: {}", isVisible);
            return isVisible;
        } catch (Exception e) {
            logger.warn("Error checking visibility of Continue button: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Clicks the "Continue without an account" button.
     */
    public void clickContinueButton() {
        try {
            logger.info("Clicking Continue button...");
            WaitUtils.waitForElementClickable(CONTINUE_BUTTON).click();
        } catch (Exception e) {
            logger.error("Error clicking Continue button: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Checks if the age verification button is visible.
     *
     * @return True if the button is visible, false otherwise
     */
    public boolean isAgeVerificationVisible() {
        try {
            boolean isVisible = WaitUtils.waitForElementVisible(AGE_VERIFICATION_BUTTON) != null;
            logger.info("Age verification button visibility: {}", isVisible);
            return isVisible;
        } catch (Exception e) {
            logger.warn("Error checking visibility of Age Verification button: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Clicks the age verification button.
     */
    public void clickAgeVerification() {
        try {
            logger.info("Clicking Age Verification button...");
            WaitUtils.waitForElementClickable(AGE_VERIFICATION_BUTTON).click();
        } catch (Exception e) {
            logger.error("Error clicking Age Verification button: {}", e.getMessage(), e);
            throw e;
        }
    }
}