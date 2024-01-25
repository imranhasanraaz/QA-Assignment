package utils;

import com.microsoft.playwright.Locator;

import java.util.Properties;

public abstract class WebActions {

    public static String getProperty(String key) {
        ConfigReader configReader = new ConfigReader();
        Properties properties = configReader.initProp();
        return properties.getProperty(key);
    }

    public static boolean waitUntilElementDisplayed(Locator locator, int timeoutSec) {
        boolean elementVisible = locator.isVisible();
        int timer = 0;
        while (!elementVisible && timer < timeoutSec) {
            try {
                Thread.sleep(1000);
                elementVisible = locator.isVisible();
                timer++;

            } catch (Exception e) {
                System.out.println(locator + "was not visible.");
            }
        }
        return elementVisible;
    }
}
