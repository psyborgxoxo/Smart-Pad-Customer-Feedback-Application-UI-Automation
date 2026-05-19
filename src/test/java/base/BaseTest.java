package base;

import core.DriverManager;
import listeners.TestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseTest {

    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        logger.info("Initializing WebDriver...");
        DriverManager.getDriver(); // Initialize WebDriver
        logger.info("WebDriver initialized successfully.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        logger.info("Closing WebDriver...");
        DriverManager.quitDriver(); // Quit WebDriver
        logger.info("WebDriver closed successfully.");
    }
}



