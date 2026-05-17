package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By emailInput    = By.xpath("//div[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath("//div[text()='Password']/following-sibling::input");
    private final By loginButton   = By.xpath(
            "//div[@role='button' and @aria-describedby='button' and contains(@class,'cursor-pointer')]");

    public boolean isEmailFieldVisible() {
        try {
            waitForElement().until(ExpectedConditions.visibilityOfElementLocated(emailInput));
            return true;
        } catch (Exception e) { return false; }
    }

    public boolean isPasswordFieldVisible() {
        try {
            waitForElement().until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
            return true;
        } catch (Exception e) { return false; }
    }

    public boolean isLoginButtonVisible() {
        try {
            waitForElement().until(ExpectedConditions.visibilityOfElementLocated(loginButton));
            return true;
        } catch (Exception e) { return false; }
    }

    public void loginWithCredentials(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    private void enterEmail(String email) {
        WebElement el = waitForElement().until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        el.clear();
        el.sendKeys(email);
    }

    private void enterPassword(String password) {
        WebElement el = waitForElement().until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        el.clear();
        el.sendKeys(password);
    }

    private void clickLogin() {
        waitForElement().until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
}