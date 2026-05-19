package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ConfigurationManager {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationManager.class);
    private static final Properties properties = new Properties();

    // Default configuration values
    private static final String DEFAULT_BROWSER = "chrome";
    private static final int DEFAULT_WAIT_TIMEOUT = 15;
    private static final int DEFAULT_IMPLICIT_WAIT = 10;
    private static final int DEFAULT_PAGE_LOAD_TIMEOUT = 60;

    // Static block to load configuration file
    static {
        try {
            String env = System.getProperty("env", "default");
            String configFile = "config.properties";
            properties.load(ConfigurationManager.class
                    .getClassLoader()
                    .getResourceAsStream(configFile));
            logger.info("Successfully loaded {}", configFile);
        } catch (Exception e) {
            logger.error("Error loading configuration file: {}", e.getMessage(), e);
        }
    }

    /**
     * Retrieves a property value from system properties or config.properties.
     *
     * @param key Property key
     * @return Property value or null if not found
     */
    private static String getProperty(String key) {
        String value = System.getProperty(key, properties.getProperty(key));
        if (value == null || value.trim().isEmpty()) {
            logger.warn("Property '{}' not found or is empty in system properties or config.properties", key);
            return null;
        }
        return value;
    }

    /**
     * Retrieves the base URL for the application.
     *
     * @return Base URL
     */
    public static String getBaseUrl() {
        String baseUrl = System.getProperty("base.url", properties.getProperty("base.url"));
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            throw new IllegalStateException("Base URL is not configured in system properties or config.properties");
        }
        if (!baseUrl.matches("^(http|https)://.*")) {
            throw new IllegalArgumentException("Invalid base URL format: " + baseUrl);
        }
        return baseUrl;
    }

    /**
     * Retrieves the browser name for WebDriver initialization.
     *
     * @return Browser name (default: chrome)
     */
    public static String getBrowser() {
        String browser = System.getProperty("browser", properties.getProperty("browser", DEFAULT_BROWSER));
        logger.info("Selected browser: {}", browser);
        return browser;
    }

    /**
     * Retrieves the global timeout value.
     *
     * @return Timeout in seconds (default: 15)
     */
    public static int getTimeout() {
        return parseIntegerProperty("timeout", DEFAULT_WAIT_TIMEOUT);
    }

    /**
     * Retrieves the explicit wait timeout value.
     *
     * @return Wait timeout in seconds (default: 15)
     */
    public static int getWaitTimeout() {
        return parseIntegerProperty("waitTimeout", DEFAULT_WAIT_TIMEOUT);
    }

    /**
     * Retrieves the implicit wait timeout value.
     *
     * @return Implicit wait timeout in seconds (default: 10)
     */
    public static int getImplicitWaitTimeout() {
        return parseIntegerProperty("implicit.wait", DEFAULT_IMPLICIT_WAIT);
    }

    /**
     * Retrieves the page load timeout value.
     *
     * @return Page load timeout in seconds (default: 60)
     */
    public static int getPageLoadTimeout() {
        return parseIntegerProperty("page.load.timeout", DEFAULT_PAGE_LOAD_TIMEOUT);
    }

    /**
     * Parses an integer property value with a fallback default.
     *
     * @param key          Property key
     * @param defaultValue Default value if property is missing or invalid
     * @return Parsed integer value or default
     */
    private static int parseIntegerProperty(String key, int defaultValue) {
        String value = getProperty(key);
        if (value != null) {
            try {
                int parsedValue = Integer.parseInt(value);
                if (parsedValue <= 0) {
                    logger.warn("Invalid value for '{}': {}. Using default: {}", key, value, defaultValue);
                    return defaultValue;
                }
                return parsedValue;
            } catch (NumberFormatException e) {
                logger.warn("Failed to parse '{}' as integer: {}. Using default: {}", key, value, defaultValue);
            }
        } else {
            logger.warn("Property '{}' not found. Using default: {}", key, defaultValue);
        }
        return defaultValue;
    }

    /**
     * Reloads the configuration dynamically.
     */
    public static void reloadConfiguration() {
        try {
            properties.clear();
            String env = System.getProperty("env", "default");
            String configFile = "config." + env + ".properties";
            properties.load(ConfigurationManager.class
                    .getClassLoader()
                    .getResourceAsStream(configFile));
            logger.info("Reloaded {}", configFile);
        } catch (Exception e) {
            logger.error("Error reloading configuration file: {}", e.getMessage(), e);
        }
    }
}