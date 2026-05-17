package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthPage extends BasePage {

    private final By continueBtn  = By.xpath("//div[@role='button' and text()='Continue without an account']");
    private final By ageVerifyBtn = By.xpath("//button[contains(text(),'Yes')]");

    public boolean isContinueButtonVisible() {
        try {
            waitForElement().until(ExpectedConditions.visibilityOfElementLocated(continueBtn));
            return true;
        } catch (Exception e) { return false; }
    }

    public void clickContinueButton() {
        waitForElement().until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public boolean isAgeVerificationVisible() {
        try {
            waitForElement().until(ExpectedConditions.visibilityOfElementLocated(ageVerifyBtn));
            return true;
        } catch (Exception e) { return false; }
    }

    public void clickAgeVerification() {
        waitForElement().until(ExpectedConditions.elementToBeClickable(ageVerifyBtn)).click();
    }
}