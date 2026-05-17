// package listeners;

// import core.DriverManager;
// import io.qameta.allure.Attachment;
// import org.openqa.selenium.OutputType;
// import org.openqa.selenium.TakesScreenshot;
// import org.openqa.selenium.WebDriver;
// import org.testng.ITestListener;
// import org.testng.ITestResult;

// public class TestListener implements ITestListener {

//     @Override
//     public void onTestFailure(ITestResult result) {
//         WebDriver driver = DriverManager.getDriver();
//         if (driver != null) {
//             saveScreenshot(driver);
//         }
//     }

//     @Attachment(value = "Page Screenshot on Failure", type = "image/png")
//     public byte[] saveScreenshot(WebDriver driver) {
//         try {
//             return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//         } catch (Exception e) {
//             System.err.println("Could not take screenshot: " + e.getMessage());
//             return new byte[0];
//         }
//     }
// }

package listeners;

import core.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Date;

public class TestListener implements ITestListener {

    // =========================
    // TEST START
    // =========================
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🚀 STARTED: " + result.getName());
    }

    // =========================
    // TEST SUCCESS
    // =========================
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ PASSED: " + result.getName());

        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            attachScreenshot(result.getName(), driver);
        }
    }

    // =========================
    // TEST FAILURE
    // =========================
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ FAILED: " + result.getName());
        System.out.println("Reason: " + result.getThrowable());

        WebDriver driver = DriverManager.getDriver();

        if (driver != null) {
            attachScreenshot(result.getName(), driver);
            attachPageSource(driver);
            attachConsoleLogs(driver);
        }
    }

    // =========================
    // TEST SKIPPED
    // =========================
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠️ SKIPPED: " + result.getName());
    }

    // =========================
    // ATTACHMENTS
    // =========================

    @Attachment(value = "{0} - Screenshot", type = "image/png")
    public byte[] attachScreenshot(String testName, WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            System.err.println("Screenshot failed: " + e.getMessage());
            return new byte[0];
        }
    }

    @Attachment(value = "Page Source", type = "text/html")
    public String attachPageSource(WebDriver driver) {
        try {
            return driver.getPageSource();
        } catch (Exception e) {
            return "Unable to capture page source: " + e.getMessage();
        }
    }

    @Attachment(value = "Browser Console Logs", type = "text/plain")
    public String attachConsoleLogs(WebDriver driver) {
        StringBuilder logs = new StringBuilder();

        try {
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
        }

        return logs.toString();
    }
}