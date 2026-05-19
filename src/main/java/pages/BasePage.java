package pages;

import core.DriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

public abstract class BasePage {

    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    /**
     * Retrieves the WebDriver instance.
     *
     * @return WebDriver instance
     */
    protected WebDriver driver() {
        return DriverManager.getDriver();
    }

    /**
     * Retrieves the title of the current page.
     *
     * @return Page title
     */
    public String getPageTitle() {
        String title = driver().getTitle();
        logger.info("Current page title: {}", title);
        return title;
    }

    /**
     * Retrieves the current URL of the browser.
     *
     * @return Current URL
     */
    public String getCurrentUrl() {
        String url = driver().getCurrentUrl();
        logger.info("Current page URL: {}", url);
        return url;
    }
}