package Pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TariffsPage extends BasePage {
    private ExtentTest extentTest;
    public TariffsPage(AndroidDriver<AndroidElement> androidDriver, ExtentTest extentTest) {
        super(androidDriver);
        this.extentTest = extentTest;
        PageFactory.initElements(androidDriver, this);
    }

    @FindBy(id ="de.ottonova.mobile:id/title")
    WebElement pageTitle;

    @FindBy(id ="de.ottonova.mobile:id/tariffListCalculate")
    WebElement calculatePremiumButton;

    @Override
    public boolean verifyPage() {
        if(isElementPresent(pageTitle)&& isElementPresent(calculatePremiumButton)){
            extentTest.log(Status.INFO, "On Our Tariffs page ");
            return true;
        }
        else{
            extentTest.log(Status.INFO,"No title element found on Events page");
            return false;
        }
    }

    public void clickCalculatePremiumButton() {
        if(calculatePremiumButton.isEnabled()) {
            try {
                calculatePremiumButton.click();

                extentTest.log(Status.INFO, "Calculate Premium Clicked");
            }
            catch (Exception e) {
                extentTest.fail("Failed to click on Calculate Premium button");
            }
        }
        else {
            extentTest.fail("Calculate Premium not present");
        }
    }
}
