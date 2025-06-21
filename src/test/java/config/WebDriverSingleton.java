package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverSingleton {
    private static WebDriver driver;

    public static WebDriver getInstance() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized", "--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    // public static void quit() {
    // try {
    // if (driver != null) {
    // driver.quit();
    // }
    // } finally {
    // driver = null;
    // }
    // }

    public static void quit() {
        try {
            System.out.println("Attempting to quit driver...");
            if (driver != null) {
                System.out.println("Driver session ID: " + ((ChromeDriver) driver).getSessionId());
                driver.quit();
                System.out.println("Driver quit successfully");
            }
        } catch (Exception e) {
            System.out.println("FAILED to quit driver: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver = null;
        }
    }
}