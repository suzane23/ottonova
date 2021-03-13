package Pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends BasePage {
    private ExtentTest extentTest;

   public ProfilePage(AndroidDriver<AndroidElement> androidDriver, ExtentTest extentTest) {

        super(androidDriver);
        this.extentTest = extentTest;
        PageFactory.initElements(androidDriver, this);
    }

    @FindBy(id = "de.ottonova.mobile:id/bottomBarTitle")
    WebElement profile;

    @FindBy(id = "de.ottonova.mobile:id/profileMasterName")
    WebElement profileMasterName;


    @FindBy(xpath = "//android.widget.TextView[contains(@text, 'Our Tariffs')]")
    WebElement ourTariffs;


    @Override
    public boolean verifyPage() {
        if(isElementPresent(profile)&& profile.isEnabled()&&(isElementPresent(profileMasterName) && isElementPresent(ourTariffs))){
            extentTest.log(Status.INFO,"On Profile page");
            return true;
        }
        else{
           extentTest.fail("No title element found on Profile page");
            return false;
        }
    }


    public void clickOurTariffs(){
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(ourTariffs.isEnabled() ) {
            try {
                ourTariffs.click();

                extentTest.log(Status.INFO, "Our Tariffs Clicked");
            }
            catch (Exception e) {
                extentTest.fail("Failed to click on Our Tariffs button");
            }
        } else {
            extentTest.fail("Our Tariffs is not present");
        }

    }



}
