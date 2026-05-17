package config;

import java.util.Properties;

public class ConfigurationManager {

    private static final Properties properties = new Properties();

    private static final String DEFAULT_BROWSER          = "chrome";
    private static final int    DEFAULT_WAIT_TIMEOUT     = 15;
    private static final int    DEFAULT_IMPLICIT_WAIT    = 10;
    private static final int    DEFAULT_PAGE_LOAD_TIMEOUT = 60;

    static {
        try {
            properties.load(ConfigurationManager.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties"));
        } catch (Exception e) {
            System.err.println("Error loading config.properties: " + e.getMessage());
        }
    }

    private static String getProperty(String key) {
        return System.getProperty(key, properties.getProperty(key));
    }

    public static String getBaseUrl() {
        return System.getProperty("baseUrl", properties.getProperty("baseUrl"));
    }

    public static String getBrowser() {
        return System.getProperty("browser",
                properties.getProperty("browser", DEFAULT_BROWSER));
    }

    public static int getTimeout() {
        String val = getProperty("timeout");
        return val != null ? Integer.parseInt(val) : DEFAULT_WAIT_TIMEOUT;  // was hardcoded 15
    }

    public static int getWaitTimeout() {
        String val = getProperty("waitTimeout");
        return val != null ? Integer.parseInt(val) : DEFAULT_WAIT_TIMEOUT;  // was calling getTimeout()
    }

    public static int getImplicitWaitTimeout() {
        return Integer.parseInt(System.getProperty("implicit.wait",
                properties.getProperty("implicit.wait",
                        String.valueOf(DEFAULT_IMPLICIT_WAIT))));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(System.getProperty("page.load.timeout",
                properties.getProperty("page.load.timeout",
                        String.valueOf(DEFAULT_PAGE_LOAD_TIMEOUT))));
    }
}