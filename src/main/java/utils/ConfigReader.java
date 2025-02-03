package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Utility class to read configuration values from a JSON file.
 * This class provides methods to retrieve configuration values such as website URL and browser type
 * from the config.json file.
 */
public class ConfigReader {

    // Path to the configuration JSON file
    private static final String CONFIG_FILE_PATH = "/Users/rafeqfiad/IdeaProjects/AutomationProject/src/main/resources/config.json";

    /**
     * Retrieves the website URL from the config file.
     *
     * @return The website URL as a string.
     */
    public static String getWebsiteUrl() {
        return getConfigValue("websiteUrl");
    }

    /**
     * Retrieves the browser type from the config file.
     *
     * @return The browser type as a string.
     */
    public static String getBrowserType() {
        return getConfigValue("browserType");
    }

    /**
     * Helper method to retrieve the value associated with a specific key from the config file.
     *
     * @param key The key whose value needs to be fetched.
     * @return The value associated with the key, or null if there is an error.
     */
    private static String getConfigValue(String key) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Reading the JSON file and parsing it into a JsonNode object
            JsonNode rootNode = objectMapper.readTree(new File(CONFIG_FILE_PATH));
            // Returning the value associated with the provided key
            return rootNode.get(key).asText();
        } catch (IOException e) {
            // If there is an IOException, print the stack trace
            e.printStackTrace();
            return null;
        }
    }
}
