package Pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class WebPage extends BasePage {

    private final static String URL = "https://www.ottonova.de/online-beitragsrechner/?utm_source=content_referral&utm_medium=ottonova_app&utm_campaign=tariff";

    private ExtentTest extentTest;
    public WebPage(AndroidDriver<AndroidElement> androidDriver, ExtentTest extentTest) {

        super(androidDriver);
        this.extentTest = extentTest;
        PageFactory.initElements(androidDriver, this);
    }

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Share\"]")
    WebElement shareButton;

    @FindBy(id = "android:id/chooser_copy_button")
    WebElement copyButton;

    @Override
    public boolean verifyPage() {

        return false;
    }

    public void click() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Set<String> contexts = androidDriver.getContextHandles();
       // System.out.println(contexts.toArray()[1]);
        try {
            androidDriver.context(String.valueOf(contexts.toArray()[1]));
        } catch (Exception e) {
            e.printStackTrace();
            extentTest.fail(e.getMessage());
        }
        extentTest.log(Status.INFO, "Switched to WebView");
        String copiedURL = androidDriver.getCurrentUrl();
        extentTest.log(Status.INFO, "Actual URL " + copiedURL);
        extentTest.log(Status.INFO, "Expected URL " + URL );
        if (copiedURL.equals(URL)) {
            System.out.println("URL matches");
        } else {
            System.out.println("URL not matched");
        }

        try {
            androidDriver.context("NATIVE_APP");
        } catch (Exception e) {
            e.printStackTrace();
            extentTest.fail(e.getMessage());
        }
        extentTest.log(Status.INFO, "Switched to NATIVE_APP");
    }
}


