package listeners;

import core.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Date;

public class TestListener implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    // =========================
    // TEST EVENTS
    // =========================

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("🚀 STARTED: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("✅ PASSED: {}", result.getName());

        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            attachScreenshot(result.getName(), driver);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("❌ FAILED: {}", result.getName());
        logger.error("Reason: {}", result.getThrowable());

        // Log stack trace for debugging
        if (result.getThrowable() != null) {
            logger.error("Stack Trace:", result.getThrowable());
        }

        WebDriver driver = DriverManager.getDriver();

        if (driver != null) {
            attachScreenshot(result.getName(), driver);
            attachPageSource(driver);
            attachConsoleLogs(driver);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("⚠️ SKIPPED: {}", result.getName());
    }

    // =========================
    // ATTACHMENTS
    // =========================

    @Attachment(value = "{0} - Screenshot", type = "image/png")
    public byte[] attachScreenshot(String testName, WebDriver driver) {
        try {
            logger.info("Capturing screenshot for test: {}", testName);
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            logger.error("Failed to capture screenshot for test {}: {}", testName, e.getMessage());
            return new byte[0];
        }
    }

    @Attachment(value = "Page Source", type = "text/html")
    public String attachPageSource(WebDriver driver) {
        try {
            logger.info("Capturing page source...");
            return driver.getPageSource();
        } catch (Exception e) {
            logger.error("Failed to capture page source: {}", e.getMessage());
            return "Unable to capture page source: " + e.getMessage();
        }
    }

    @Attachment(value = "Browser Console Logs", type = "text/plain")
    public String attachConsoleLogs(WebDriver driver) {
        StringBuilder logs = new StringBuilder();

        try {
            logger.info("Fetching browser console logs...");
            LogEntries logEntries = driver.manage().logs().get("browser");

            for (LogEntry entry : logEntries) {
                logs.append(new Date(entry.getTimestamp()))
                        .append(" ")
                        .append(entry.getLevel())
                        .append(" ")
                        .append(entry.getMessage())
                        .append("\n");
            }

        } catch (Exception e) {
            logs.append("Unable to fetch browser logs: ").append(e.getMessage());
            logger.error("Error fetching browser logs: {}", e.getMessage());
        }

        return logs.toString();
    }
}