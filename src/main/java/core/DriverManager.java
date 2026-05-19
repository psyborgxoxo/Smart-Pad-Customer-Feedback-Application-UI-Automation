package core;

import config.ConfigurationManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class DriverManager {

    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            initDriver();
            if (driverThreadLocal.get() == null) {
                throw new RuntimeException("WebDriver initialization failed - driver is null");
            }
        }
        return driverThreadLocal.get();
    }

    private static void initDriver() {
        String browser = ConfigurationManager.getBrowser().toLowerCase();
        WebDriver driver = null;

        try {
            logger.info("Initializing WebDriver for browser: {}", browser);

            switch (browser) {
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    configureHeadlessMode(firefoxOptions);
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                case "edge":
                    driver = new EdgeDriver();
                    break;

                case "chrome":
                default:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    configureHeadlessMode(chromeOptions);
                    chromeOptions.addArguments("--disable-gpu", "--window-size=1920,1080",
                            "--ignore-certificate-errors", "--no-sandbox", "--disable-dev-shm-usage");
                    chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                    chromeOptions.setExperimentalOption("useAutomationExtension", false);
                    driver = new ChromeDriver(chromeOptions);

                    // Anti-detection script
                    ((JavascriptExecutor) driver).executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
                    break;
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigurationManager.getImplicitWaitTimeout()));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigurationManager.getPageLoadTimeout()));

            driverThreadLocal.set(driver);
            logger.info("WebDriver initialized successfully for browser: {}", browser);

        } catch (Exception e) {
            logger.error("Failed to initialize WebDriver for browser {}: {}", browser, e.getMessage(), e);
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
        }
    }

    private static void configureHeadlessMode(Object options) {
        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            if (options instanceof ChromeOptions) {
                ((ChromeOptions) options).addArguments("--headless=new");
            } else if (options instanceof FirefoxOptions) {
                ((FirefoxOptions) options).addArguments("--headless");
            }
        }
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            try {
                logger.info("Quitting WebDriver...");
                driverThreadLocal.get().quit();
            } catch (Exception e) {
                logger.error("Error quitting WebDriver: {}", e.getMessage(), e);
            } finally {
                driverThreadLocal.remove();
                logger.info("WebDriver quit and removed from ThreadLocal.");
            }
        }
    }

    public static boolean isDriverInitialized() {
        return driverThreadLocal.get() != null;
    }
}