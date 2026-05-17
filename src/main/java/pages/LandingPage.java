package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LandingPage extends BasePage {

    private final By appHeader     = By.xpath("//*[contains(.,'kristalball')]");
    private final By getStartedBtn = By.xpath("//div[@role='button' and text()='Get started']");
    private final By ginElement    = By.xpath("//div[text()='Gin']");

    public void navigateTo(String url) {
        driver().get(url);
    }

    public boolean isAppHeaderVisible() {
        try {
            waitForElement().until(ExpectedConditions.visibilityOfElementLocated(appHeader));
            return true;
        } catch (Exception e) { return false; }
    }

    public boolean isGetStartedButtonDisplayed() {
        try {
            waitForElement().until(ExpectedConditions.visibilityOfElementLocated(getStartedBtn));
            return true;
        } catch (Exception e) { return false; }
    }

    public void clickGetStartedButton() {
        waitForElement().until(ExpectedConditions.elementToBeClickable(getStartedBtn)).click();
    }

    public boolean isGinElementVisible() {
        try {
            waitForElement().until(ExpectedConditions.visibilityOfElementLocated(ginElement));
            return true;
        } catch (Exception e) { return false; }
    }

    public void clickGinElement() {
        waitForElement().until(ExpectedConditions.elementToBeClickable(ginElement)).click();
    }
}