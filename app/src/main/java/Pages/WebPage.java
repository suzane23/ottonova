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

    private final static String URL_TO_MATCH = "https://www.ottonova.de/online-beitragsrechner/?utm_source=content_referral&utm_medium=ottonova_app&utm_campaign=tariff";

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

    public void verifyURL() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Set<String> contexts = androidDriver.getContextHandles();

        for (String context: contexts) {
            if(context.contains("WEBVIEW")) {
                try {
                    androidDriver.context(context);

                    extentTest.log(Status.INFO, "Switched to WebView");
                    String copiedURL = androidDriver.getCurrentUrl();
                    extentTest.log(Status.INFO, "Actual URL " + copiedURL);
                    extentTest.log(Status.INFO, "Expected URL " + URL_TO_MATCH);
                    if (copiedURL.equals(URL_TO_MATCH)) {
                        extentTest.log(Status.INFO, "URL matches");
                    } else {
                        extentTest.log(Status.INFO, "URL not matched");
                    }

                    switchContextToNative();

                } catch (Exception e) {
                    extentTest.fail("Failed to get web URL");
                }
            }
        }
    }

    private void switchContextToNative(){
        try {
            androidDriver.context("NATIVE_APP");

            extentTest.log(Status.INFO, "Switched to NATIVE_APP");
        }
        catch (Exception e) {
            extentTest.warning("Failed to Switch context to NATIVE_APP");
        }
    }
}


