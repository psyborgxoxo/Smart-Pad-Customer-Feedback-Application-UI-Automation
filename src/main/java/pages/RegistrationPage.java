package pages;

import org.openqa.selenium.By;
import utils.ActionUtils;
import utils.WaitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationPage.class);

    // Locators
    private static final By SWITCH_TO_REGISTER_BUTTON = By.xpath("//div[@role='button' and contains(text(), 'have an account')]");
    private static final By NAME_INPUT = By.xpath("//div[text()='Name']/following-sibling::input");
    private static final By EMAIL_INPUT = By.xpath("//div[text()='Email']/following-sibling::input");
    private static final By PASSWORD_INPUT = By.xpath("//div[text()='Password']/following-sibling::input");
    private static final By CONFIRM_PASSWORD_INPUT = By.xpath("//div[text()='Confirm Password']/following-sibling::input");
    private static final By TERMS_CHECKBOX = By.xpath("//div[contains(@class, 'flex-row')]//input[@type='checkbox']");
    private static final By SIGN_UP_BUTTON = By.xpath(
            "//div[@role='button' and @aria-describedby='button' and contains(@class,'cursor-pointer')]");
    private static final By VERIFY_EMAIL_TEXT = By.xpath(
            "//div[contains(@class, 'flex-col')]//div[text()='Verify your email']");

    /**
     * Registers a new user with the provided details.
     *
     * @param name     User's name
     * @param email    User's email
     * @param password User's password
     */
    public void registerNewUser(String name, String email, String password) {
        logger.info("Registering new user with name: {}, email: {}, password: {}", name, email, "********");
        clickSwitchToRegister();
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(password);
        acceptTerms();
        clickSignUp();
    }

    /**
     * Checks if the OTP verification text is visible.
     *
     * @return True if the OTP verification text is visible, false otherwise
     */
    public boolean isOtpVerificationVisible() {
        try {
            boolean isVisible = WaitUtils.waitForElementVisible(VERIFY_EMAIL_TEXT) != null;
            logger.info("OTP verification text visibility: {}", isVisible);
            return isVisible;
        } catch (Exception e) {
            logger.warn("Error checking visibility of OTP verification text: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Clicks the "Switch to Register" button.
     */
    private void clickSwitchToRegister() {
        logger.info("Clicking Switch to Register button...");
        ActionUtils.click(SWITCH_TO_REGISTER_BUTTON);
    }

    /**
     * Enters the name into the name input field.
     *
     * @param name User's name
     */
    private void enterName(String name) {
        logger.info("Entering name: {}", name);
        ActionUtils.type(NAME_INPUT, name);
    }

    /**
     * Enters the email into the email input field.
     *
     * @param email User's email
     */
    private void enterEmail(String email) {
        logger.info("Entering email: {}", email);
        ActionUtils.type(EMAIL_INPUT, email);
    }

    /**
     * Enters the password into the password input field.
     *
     * @param password User's password
     */
    private void enterPassword(String password) {
        logger.info("Entering password...");
        ActionUtils.type(PASSWORD_INPUT, password);
    }

    /**
     * Enters the confirm password into the confirm password input field.
     *
     * @param confirmPassword User's confirm password
     */
    private void enterConfirmPassword(String confirmPassword) {
        logger.info("Entering confirm password...");
        ActionUtils.type(CONFIRM_PASSWORD_INPUT, confirmPassword);
    }

    /**
     * Accepts the terms and conditions checkbox.
     */
    private void acceptTerms() {
        logger.info("Accepting terms and conditions...");
        ActionUtils.click(TERMS_CHECKBOX);
    }

    /**
     * Clicks the Sign Up button.
     */
    private void clickSignUp() {
        logger.info("Clicking Sign Up button...");
        ActionUtils.click(SIGN_UP_BUTTON);
    }
}