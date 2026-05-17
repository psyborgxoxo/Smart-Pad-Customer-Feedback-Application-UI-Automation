package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FeedbackPage extends BasePage {

    private final By title      = By.xpath("//h6[contains(text(),'Feedback')]");
    private final By nameInput  = By.xpath("//input[@placeholder='Type your name here...']");
    private final By emailInput = By.xpath("//input[@placeholder='Type your email here...']");
    private final By rating     = By.xpath("(//div[contains(@class,'cursor-pointer')])[5]");
    private final By comment    = By.xpath("//textarea[@placeholder='Type your comments here...']");
    private final By submitBtn  = By.xpath("//div[@role='button' and .//p[text()='Submit']]");
    private final By thankYou   = By.xpath("//div[text()='Thankyou!']");

    public boolean isVisible() {
        try {
            waitForElement().until(ExpectedConditions.visibilityOfElementLocated(title));
            return true;
        } catch (Exception e) { return false; }
    }

    public boolean isThankYouVisible() {
        try {
            waitForElement().until(ExpectedConditions.visibilityOfElementLocated(thankYou));
            return true;
        } catch (Exception e) { return false; }
    }

    public void submitFeedback(String name, String email, String commentText) {
        fillForm(name, email, commentText);
        clickSubmit();
    }

    public void submitWithoutFilling() {
        clickSubmit();
    }

    private void fillForm(String name, String email, String commentText) {
        waitForElement().until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
        waitForElement().until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        waitForElement().until(ExpectedConditions.elementToBeClickable(rating)).click();
        waitForElement().until(ExpectedConditions.visibilityOfElementLocated(comment)).sendKeys(commentText);
    }

    private void clickSubmit() {
        waitForElement().until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }
}