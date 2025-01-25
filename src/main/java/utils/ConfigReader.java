package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ConfigReader {

    private static final String CONFIG_FILE_PATH = "/Users/rafeqfiad/IdeaProjects/AutomationProject/src/main/resources/config.json";  // Path to your config.json

    // Method to read values from the JSON file
    public static String getWebsiteUrl() {
        return getConfigValue("websiteUrl");
    }

    public static String getBrowserType() {
        return getConfigValue("browserType");
    }

    private static String getConfigValue(String key) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(new File(CONFIG_FILE_PATH));
            return rootNode.get(key).asText();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

