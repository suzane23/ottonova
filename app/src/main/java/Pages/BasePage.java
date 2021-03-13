package Pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class BasePage {
    protected WebDriverWait wait;
    protected AndroidDriver<AndroidElement> androidDriver;

    public BasePage(AndroidDriver<AndroidElement> androidDriver) {
        this.androidDriver = androidDriver;
    }

    public abstract boolean verifyPage();

    protected boolean isElementPresent(WebElement element) {
        try {
            wait = new WebDriverWait(androidDriver, 1);
            wait.until(ExpectedConditions.visibilityOf(element));
            androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }
}
