package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import config.WebDriverSingleton;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public abstract class BasePage {
    final WebDriver driver = WebDriverSingleton.getInstance();
    final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.clear();
        element.sendKeys(text);
    }

    void selectDropdown(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        new Select(element).selectByVisibleText(value);
    }

    boolean isElementVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)) != null;
        } catch (Exception e) {
            return false;
        }
    }

    protected void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}