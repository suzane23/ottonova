package Pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LoginPage extends BasePage {
    private ExtentTest extentTest;

    public LoginPage(AndroidDriver<AndroidElement> androidDriver, ExtentTest extentTest) {
        super(androidDriver);
        this.extentTest = extentTest;

        PageFactory.initElements(androidDriver, this);
    }

    @FindBy(id = "de.ottonova.mobile:id/freemiumButton")
    WebElement testAppButton;

    @FindBy(id = "de.ottonova.mobile:id/loginButton")
    WebElement loginButton;


    @Override
    public boolean verifyPage() {
        return false;
    }



    public void clickTestAppButton(){
        if(isElementPresent(testAppButton)) {
            try {
                androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                testAppButton.click();
            } catch (Exception e) {
                e.printStackTrace();
                extentTest.fail(e.getMessage());
            }
            extentTest.log(Status.INFO, "Test App Button Clicked");
        } else {
            extentTest.fail("Test App button not present");
        }

    }
}
