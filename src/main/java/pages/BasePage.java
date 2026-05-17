package pages;

import core.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {

    private static final int DEFAULT_WAIT_SECONDS = 15;

    protected WebDriver driver() {
        return DriverManager.getDriver();
    }

    protected WebDriverWait waitForElement() {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_WAIT_SECONDS));
    }

    public String getPageTitle() {
        return driver().getTitle();
    }

    public String getCurrentUrl() {
        return driver().getCurrentUrl();
    }
}