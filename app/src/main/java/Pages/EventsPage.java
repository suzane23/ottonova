package Pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class EventsPage extends BasePage {
    private ExtentTest extentTest;

    public EventsPage(AndroidDriver<AndroidElement> androidDriver, ExtentTest extentTest) {
        super(androidDriver);
        this.extentTest = extentTest;
        PageFactory.initElements(androidDriver, this);
    }

    @FindBy(id = "de.ottonova.mobile:id/health_prompt_container")
    WebElement healthPromptContainer;

    @FindBy(id = "de.ottonova.mobile:id/message")
    WebElement healthPrompt;

    @FindBy(id = "de.ottonova.mobile:id/ottoToolbarDefaultTitle")
    WebElement EventTitle;

    @FindBy(id = "de.ottonova.mobile:id/bottomBarTitle")
    WebElement eventsBottomBarTitle;

    @FindBy(xpath = "//android.widget.TextView[contains(@text, 'Concierge')]")
    WebElement concierge;

    @FindBy(xpath = "//android.widget.TextView[contains(@text, 'Documents')]")
    WebElement documents;

    @FindBy(xpath = "//android.widget.TextView[contains(@text, 'Profile')]")
    WebElement profile;

    @Override
    public boolean verifyPage() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(isElementPresent(EventTitle)&& isElementPresent(profile) && EventTitle.isEnabled()){
            extentTest.log(Status.INFO, "On Events page");
            return true;
        }
        else{
            extentTest.fail("No title element found on Events page");
            return false;
        }
    }

  public void getEventCardsAndCount(){
        int eventsCardCount = 0;
        try {

            String messageElementText = healthPrompt.getText();
            extentTest.log(Status.INFO,"Message = " + messageElementText);

            eventsCardCount++;

            String nextMessage;

            Dimension dimension = healthPromptContainer.getSize();
            int scrollStart = (int) (dimension.getWidth()*0.8);
            int scrollEnd = (int) (dimension.getWidth()*0.2);

            int ycoordinate = dimension.height + 200;

            boolean bContinue;

            do {

                new TouchAction(androidDriver)
                        .press(PointOption.point(scrollStart,ycoordinate))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(PointOption.point(scrollEnd,ycoordinate)).release().perform();

                try {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e) {

                }

                nextMessage = healthPrompt.getText();

                if(!messageElementText.equalsIgnoreCase(nextMessage)){
                    messageElementText = nextMessage;
                    extentTest.log(Status.INFO,"Message = " + nextMessage);
                    bContinue = true;
                    eventsCardCount++;
                }
                else {
                    bContinue = false;
                }

            }while (bContinue);

            extentTest.log(Status.INFO,"Total event cards = " + eventsCardCount);
        }
        catch (Exception e) {
            extentTest.fail(e.getMessage());
        }
    }

    public void goToProfile(){
        if(isElementPresent(profile)) {
            try {
                androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                profile.click();
                extentTest.log(Status.INFO, "Profile Clicked");
            }
            catch (Exception e) {
                extentTest.fail("Failed to click Profile button");
            }
        } else {
            extentTest.fail("Profile not present");
        }
    }
}
