package core;

import config.ConfigurationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverManager {

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
        WebDriver driver;

        try {
            switch (browser) {
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
                        firefoxOptions.addArguments("--headless");
                    }
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                case "edge":
                    driver = new EdgeDriver();
                    break;

                case "chrome":
                default:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
                        chromeOptions.addArguments("--headless=new");
                    }
                    chromeOptions.addArguments("--disable-gpu", "--window-size=1920,1080",
                            "--ignore-certificate-errors");
                    chromeOptions.setExperimentalOption("excludeSwitches",
                            new String[]{"enable-automation"});
                    chromeOptions.setExperimentalOption("useAutomationExtension", false);
                    driver = new ChromeDriver(chromeOptions);
                    break;
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(
                    Duration.ofSeconds(ConfigurationManager.getImplicitWaitTimeout()));  // was missing
            driver.manage().timeouts().pageLoadTimeout(
                    Duration.ofSeconds(ConfigurationManager.getPageLoadTimeout()));      // was hardcoded 60

            driverThreadLocal.set(driver);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
        }
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            try {
                driverThreadLocal.get().quit();
            } catch (Exception e) {
                System.err.println("Error quitting driver: " + e.getMessage());
            } finally {
                driverThreadLocal.remove();
            }
        }
    }

    public static boolean isDriverInitialized() {
        return driverThreadLocal.get() != null;
    }
}