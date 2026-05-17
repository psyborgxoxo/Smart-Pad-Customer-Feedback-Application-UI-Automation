package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {

    private final By firstProduct     = By.xpath("(//a[contains(@href,'/auth/product')])[1]");
    private final By shareFeedbackBtn = By.xpath("//p[contains(text(),'Share Feedback')]");

    public boolean isFirstProductVisible() {
        try {
            waitForElement().until(ExpectedConditions.visibilityOfElementLocated(firstProduct));
            return true;
        } catch (Exception e) { return false; }
    }

    public void clickFirstProduct() {
        waitForElement().until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
    }

    public boolean isShareFeedbackVisible() {
        try {
            waitForElement().until(ExpectedConditions.visibilityOfElementLocated(shareFeedbackBtn));
            return true;
        } catch (Exception e) { return false; }
    }

    public void clickShareFeedback() {
        waitForElement().until(ExpectedConditions.elementToBeClickable(shareFeedbackBtn)).click();
    }
}