package pages;

import org.openqa.selenium.By;
import utils.WaitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedbackPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackPage.class);

    // Locators
    private static final By TITLE = By.xpath("//h6[contains(text(),'Feedback')]");
    private static final By NAME_INPUT = By.xpath("//input[@placeholder='Type your name here...']");
    private static final By EMAIL_INPUT = By.xpath("//input[@placeholder='Type your email here...']");
    private static final By RATING = By.xpath("(//div[contains(@class,'cursor-pointer')])[5]");
    private static final By COMMENT = By.xpath("//textarea[@placeholder='Type your comments here...']");
    private static final By SUBMIT_BUTTON = By.xpath("//div[@role='button' and .//p[text()='Submit']]");
    private static final By THANK_YOU_MESSAGE = By.xpath("//div[text()='Thankyou!']");

    /**
     * Checks if the Feedback page title is visible.
     *
     * @return True if the title is visible, false otherwise
     */
    public boolean isVisible() {
        try {
            boolean isVisible = WaitUtils.waitForElementVisible(TITLE) != null;
            logger.info("Feedback page visibility: {}", isVisible);
            return isVisible;
        } catch (Exception e) {
            logger.warn("Error checking visibility of Feedback page title: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Checks if the "Thank You" message is visible.
     *
     * @return True if the message is visible, false otherwise
     */
    public boolean isThankYouVisible() {
        try {
            boolean isVisible = WaitUtils.waitForElementVisible(THANK_YOU_MESSAGE) != null;
            logger.info("Thank You message visibility: {}", isVisible);
            return isVisible;
        } catch (Exception e) {
            logger.warn("Error checking visibility of Thank You message: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Submits feedback after filling out the form.
     *
     * @param name        User's name
     * @param email       User's email
     * @param commentText User's comment
     */
    public void submitFeedback(String name, String email, String commentText) {
        logger.info("Filling feedback form with name: {}, email: {}, comment: {}", name, email, commentText);
        fillForm(name, email, commentText);
        clickSubmit();
    }

    /**
     * Submits the feedback form without filling any fields.
     */
    public void submitWithoutFilling() {
        logger.info("Submitting feedback form without filling any fields...");
        clickSubmit();
    }

    /**
     * Fills the feedback form with the provided details.
     *
     * @param name        User's name
     * @param email       User's email
     * @param commentText User's comment
     */
    private void fillForm(String name, String email, String commentText) {
        try {
            logger.info("Filling name field...");
            WaitUtils.waitForElementVisible(NAME_INPUT).sendKeys(name);

            logger.info("Filling email field...");
            WaitUtils.waitForElementVisible(EMAIL_INPUT).sendKeys(email);

            logger.info("Selecting rating...");
            WaitUtils.waitForElementClickable(RATING).click();

            logger.info("Filling comment field...");
            WaitUtils.waitForElementVisible(COMMENT).sendKeys(commentText);
        } catch (Exception e) {
            logger.error("Error filling feedback form: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Clicks the Submit button.
     */
    private void clickSubmit() {
        try {
            logger.info("Clicking Submit button...");
            WaitUtils.waitForElementClickable(SUBMIT_BUTTON).click();
        } catch (Exception e) {
            logger.error("Error clicking Submit button: {}", e.getMessage(), e);
            throw e;
        }
    }
}