package utils;

import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;

public class AssertionUtils {

    @Step("Verify that expected text '{0}' equals actual text '{1}'")
    public static void assertEquals(String expected, String actual, String message) {
        Assertions.assertThat(actual)
                .as(message)
                .isEqualTo(expected);
    }

    @Step("Verify that expected text '{1}' is contained in actual text '{0}'")
    public static void assertContains(String actual, String expectedSubstring, String message) {
        Assertions.assertThat(actual)
                .as(message)
                .contains(expectedSubstring);
    }

    @Step("Verify that condition is true")
    public static void assertTrue(boolean condition, String message) {
        Assertions.assertThat(condition)
                .as(message)
                .isTrue();
    }
    
    @Step("Verify that condition is false")
    public static void assertFalse(boolean condition, String message) {
        Assertions.assertThat(condition)
                .as(message)
                .isFalse();
    }
}
