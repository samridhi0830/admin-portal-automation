package utilities;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class TabManager {

    private static Map<String, String> tabs = new HashMap<>();

    public static void saveTab(WebDriver driver, String tabName) {
        tabs.put(tabName, driver.getWindowHandle());
    }

    public static void openNewTab(WebDriver driver,
                                  String tabName,
                                  String url) {

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(url);

        tabs.put(tabName, driver.getWindowHandle());
    }

    public static void switchToTab(WebDriver driver, String tabName) {
        driver.switchTo().window(tabs.get(tabName));
    }
}

