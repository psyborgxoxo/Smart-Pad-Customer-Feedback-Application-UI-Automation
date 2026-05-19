package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    // Locators
    private static final By EMAIL_INPUT = By.xpath("//div[text()='Email']/following-sibling::input");
    private static final By PASSWORD_INPUT = By.xpath("//div[text()='Password']/following-sibling::input");
    private static final By LOGIN_BUTTON = By.xpath(
            "//div[@role='button' and @aria-describedby='button' and contains(@class,'cursor-pointer')]");

    /**
     * Checks if the email input field is visible.
     *
     * @return True if the email field is visible, false otherwise
     */
    public boolean isEmailFieldVisible() {
        try {
            boolean isVisible = WaitUtils.waitForElementVisible(EMAIL_INPUT) != null;
            logger.info("Email field visibility: {}", isVisible);
            return isVisible;
        } catch (Exception e) {
            logger.warn("Error checking visibility of Email field: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Checks if the password input field is visible.
     *
     * @return True if the password field is visible, false otherwise
     */
    public boolean isPasswordFieldVisible() {
        try {
            boolean isVisible = WaitUtils.waitForElementVisible(PASSWORD_INPUT) != null;
            logger.info("Password field visibility: {}", isVisible);
            return isVisible;
        } catch (Exception e) {
            logger.warn("Error checking visibility of Password field: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Checks if the login button is visible.
     *
     * @return True if the login button is visible, false otherwise
     */
    public boolean isLoginButtonVisible() {
        try {
            boolean isVisible = WaitUtils.waitForElementVisible(LOGIN_BUTTON) != null;
            logger.info("Login button visibility: {}", isVisible);
            return isVisible;
        } catch (Exception e) {
            logger.warn("Error checking visibility of Login button: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Logs in with the provided credentials.
     *
     * @param email    User's email
     * @param password User's password
     */
    public void loginWithCredentials(String email, String password) {
        logger.info("Logging in with email: {}, password: {}", email, "********");
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    /**
     * Enters the email into the email input field.
     *
     * @param email User's email
     */
    private void enterEmail(String email) {
        try {
            logger.info("Entering email: {}", email);
            WebElement el = WaitUtils.waitForElementVisible(EMAIL_INPUT);
            el.clear();
            el.sendKeys(email);
        } catch (Exception e) {
            logger.error("Error entering email: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Enters the password into the password input field.
     *
     * @param password User's password
     */
    private void enterPassword(String password) {
        try {
            logger.info("Entering password...");
            WebElement el = WaitUtils.waitForElementVisible(PASSWORD_INPUT);
            el.clear();
            el.sendKeys(password);
        } catch (Exception e) {
            logger.error("Error entering password: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Clicks the login button.
     */
    private void clickLogin() {
        try {
            logger.info("Clicking Login button...");
            WaitUtils.waitForElementClickable(LOGIN_BUTTON).click();
        } catch (Exception e) {
            logger.error("Error clicking Login button: {}", e.getMessage(), e);
            throw e;
        }
    }
}