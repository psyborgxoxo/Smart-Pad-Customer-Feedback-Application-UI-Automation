package utils;

import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class AssertionUtils {

    private static final Logger logger = LoggerFactory.getLogger(AssertionUtils.class);

    /**
     * Asserts that the actual text equals the expected text.
     *
     * @param expected Expected text
     * @param actual   Actual text
     * @param message  Custom error message
     */
    @Step("Verify that expected text '{expected}' equals actual text '{actual}'")
    public static void assertEquals(String expected, String actual, String message) {
        try {
            Assertions.assertThat(actual)
                    .as(message)
                    .isEqualTo(expected);
            logger.info("Assertion passed: Expected '{}' equals Actual '{}'", expected, actual);
        } catch (AssertionError e) {
            logger.error("Assertion failed: Expected '{}' but got '{}'. Message: {}", expected, actual, message, e);
            throw e;
        }
    }

    /**
     * Asserts that the actual text contains the expected substring.
     *
     * @param actual           Actual text
     * @param expectedSubstring Expected substring
     * @param message          Custom error message
     */
    @Step("Verify that expected text '{expectedSubstring}' is contained in actual text '{actual}'")
    public static void assertContains(String actual, String expectedSubstring, String message) {
        try {
            Assertions.assertThat(actual)
                    .as(message)
                    .contains(expectedSubstring);
            logger.info("Assertion passed: Actual '{}' contains Expected '{}'", actual, expectedSubstring);
        } catch (AssertionError e) {
            logger.error("Assertion failed: Actual '{}' does not contain Expected '{}'. Message: {}", actual, expectedSubstring, message, e);
            throw e;
        }
    }

    /**
     * Asserts that the condition is true.
     *
     * @param condition Condition to verify
     * @param message   Custom error message
     */
    @Step("Verify that condition is true")
    public static void assertTrue(boolean condition, String message) {
        try {
            Assertions.assertThat(condition)
                    .as(message)
                    .isTrue();
            logger.info("Assertion passed: Condition is true");
        } catch (AssertionError e) {
            logger.error("Assertion failed: Condition is false. Message: {}", message, e);
            throw e;
        }
    }

    /**
     * Asserts that the condition is false.
     *
     * @param condition Condition to verify
     * @param message   Custom error message
     */
    @Step("Verify that condition is false")
    public static void assertFalse(boolean condition, String message) {
        try {
            Assertions.assertThat(condition)
                    .as(message)
                    .isFalse();
            logger.info("Assertion passed: Condition is false");
        } catch (AssertionError e) {
            logger.error("Assertion failed: Condition is true. Message: {}", message, e);
            throw e;
        }
    }

    /**
     * Asserts that the object is not null.
     *
     * @param object  Object to verify
     * @param message Custom error message
     */
    @Step("Verify that object is not null")
    public static void assertNotNull(Object object, String message) {
        try {
            Assertions.assertThat(object)
                    .as(message)
                    .isNotNull();
            logger.info("Assertion passed: Object is not null");
        } catch (AssertionError e) {
            logger.error("Assertion failed: Object is null. Message: {}", message, e);
            throw e;
        }
    }

    /**
     * Asserts that the object is null.
     *
     * @param object  Object to verify
     * @param message Custom error message
     */
    @Step("Verify that object is null")
    public static void assertNull(Object object, String message) {
        try {
            Assertions.assertThat(object)
                    .as(message)
                    .isNull();
            logger.info("Assertion passed: Object is null");
        } catch (AssertionError e) {
            logger.error("Assertion failed: Object is not null. Message: {}", message, e);
            throw e;
        }
    }

    @Step("Verify that actual number '{actual}' is greater than expected number '{expected}'")
    public static void assertGreaterThan(Number actual, Number expected, String message) {
        try {
            // Ensure both actual and expected are numeric types
            if (actual == null || expected == null) {
                throw new IllegalArgumentException("Actual and expected values must not be null");
            }

            // Convert to BigDecimal for consistent comparison across numeric types
            BigDecimal actualValue = new BigDecimal(actual.toString());
            BigDecimal expectedValue = new BigDecimal(expected.toString());

            Assertions.assertThat(actualValue)
                    .as(message)
                    .isGreaterThan(expectedValue);

            logger.info("Assertion passed: Actual '{}' is greater than Expected '{}'", actual, expected);
        } catch (AssertionError e) {
            logger.error("Assertion failed: Actual '{}' is not greater than Expected '{}'. Message: {}", actual, expected, message, e);
            throw e;
        }
    }
}