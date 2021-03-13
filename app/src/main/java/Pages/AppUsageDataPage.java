package Pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class AppUsageDataPage extends BasePage {
    private ExtentTest extentTest;

    public AppUsageDataPage(AndroidDriver<AndroidElement> androidDriver, ExtentTest extentTest) {
        super(androidDriver);
        this.extentTest = extentTest;

        PageFactory.initElements(androidDriver, this);
    }

    @FindBy(xpath = "//android.widget.TextView[contains(@text, 'App usage data')]")
    private WebElement pageTitle;

    @FindBy(id = "de.ottonova.mobile:id/approveButton")
    private WebElement sendAppUsageDataButton;

    @FindBy(id = "de.ottonova.mobile:id/rejectButton")
    private WebElement doNotSendAppUsageDataButton;

    @Override
    public boolean verifyPage() {

        if(isElementPresent(pageTitle)){
            extentTest.log(Status.INFO, "On Welcome Page");
            return true;
        }
        else{
            extentTest.fail("No title element found on Welcome page");
            return false;
        }
    }


    public void clickSendAppUsageDataButton() {

        if(isElementPresent(sendAppUsageDataButton)) {

            try {
                androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                sendAppUsageDataButton.click();
            } catch (Exception e) {
                e.printStackTrace();
                extentTest.fail(e.getMessage());
            }
            extentTest.log(Status.INFO, "Send App Usage Data Button clicked");
        } else {
            extentTest.fail("Send App usage data button not present");
        }
    }

    public void clickDoNotSendAppUsageDataButton() {
        doNotSendAppUsageDataButton.click();
    }
}
