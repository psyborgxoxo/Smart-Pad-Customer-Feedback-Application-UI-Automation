package pages;

import org.openqa.selenium.By;
import utils.ActionUtils;

public class RegistrationPage extends BasePage {

    private final By switchToRegisterBtn  = By.xpath("//div[@role='button' and contains(text(), 'have an account')]");
    private final By nameInput            = By.xpath("//div[text()='Name']/following-sibling::input");
    private final By emailInput           = By.xpath("//div[text()='Email']/following-sibling::input");
    private final By passwordInput        = By.xpath("//div[text()='Password']/following-sibling::input");
    private final By confirmPasswordInput = By.xpath("//div[text()='Confirm Password']/following-sibling::input");
    private final By termsCheckbox        = By.xpath("//div[contains(@class, 'flex-row')]//input[@type='checkbox']");
    private final By signUpButton         = By.xpath(
            "//div[@role='button' and @aria-describedby='button' and contains(@class,'cursor-pointer')]");
    private final By verifyEmailText      = By.xpath(
            "//div[contains(@class, 'flex-col')]//div[text()='Verify your email']");

    public void registerNewUser(String name, String email, String password) {
        clickSwitchToRegister();
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(password);
        acceptTerms();
        clickSignUp();
    }

    public boolean isOtpVerificationVisible() {
        return ActionUtils.isDisplayed(verifyEmailText);
    }

    private void clickSwitchToRegister() { ActionUtils.click(switchToRegisterBtn); }
    private void enterName(String name)   { ActionUtils.type(nameInput, name); }
    private void enterEmail(String email) { ActionUtils.type(emailInput, email); }
    private void enterPassword(String p)  { ActionUtils.type(passwordInput, p); }
    private void enterConfirmPassword(String p) { ActionUtils.type(confirmPasswordInput, p); }
    private void acceptTerms()            { ActionUtils.click(termsCheckbox); }
    private void clickSignUp()            { ActionUtils.click(signUpButton); }
}