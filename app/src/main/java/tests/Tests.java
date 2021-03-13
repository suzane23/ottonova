package tests;

import Pages.*;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Tests extends Report{


    private AndroidDriver<AndroidElement> driver;
    DesiredCapabilities cap = new DesiredCapabilities();

    @BeforeAll
    public void setup(){


        cap.setCapability("deviceName", CapabilitiesConstants.DEVICE_NAME);
        cap.setCapability("udid", CapabilitiesConstants.DEVICE_UUID);
        cap.setCapability("platformName",CapabilitiesConstants.PLATFORM_NAME);
        cap.setCapability("platformVersion", CapabilitiesConstants.PLATFORM_VERSION);
        cap.setCapability("appPackage", CapabilitiesConstants.APP_PACKAGE);
        cap.setCapability("appActivity", CapabilitiesConstants.APP_ACTIVITY);

    }
     @BeforeEach
     public void initialise(){
         try {
             URL url = new URL("http://127.0.0.1:4723/wd/hub");
             driver = new AndroidDriver(url, cap);
         } catch (MalformedURLException e) {
             e.printStackTrace();
         }

     }


    @Test
    public void eventsCardsVerify(){
        ExtentTest test = extent.createTest("Go to events Page and Read Cards");
        test.log(Status.INFO, "Test Started");
        AppUsageDataPage dataPage = new AppUsageDataPage(driver, test);
        dataPage.verifyPage();
        dataPage.clickSendAppUsageDataButton();

        LoginPage loginPage = new LoginPage(driver, test);
        loginPage.clickTestAppButton();

        EventsPage eventsPage = new EventsPage(driver, test);
        eventsPage.verifyPage();

        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(200,200)).perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        action.tap(PointOption.point(200,200)).perform();

        eventsPage.getEventCardsAndCount();
        test.log(Status.INFO, "Test Complete");

    }

    @Test
    public void checkURL(){
        ExtentTest test = extent.createTest("Verify URL");
        test.log(Status.INFO, "Test Started");


        AppUsageDataPage dataPage = new AppUsageDataPage(driver, test);
        dataPage.verifyPage();
        dataPage.clickSendAppUsageDataButton();

        LoginPage loginPage = new LoginPage(driver, test);
        loginPage.clickTestAppButton();

        EventsPage eventsPage = new EventsPage(driver, test);
        eventsPage.verifyPage();

        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(200,200)).perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        action.tap(PointOption.point(200,200)).perform();


        eventsPage.goToProfile();

        ProfilePage profilePage = new ProfilePage(driver,test);
        profilePage.verifyPage();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        action.tap(PointOption.point(200,200)).perform();

        profilePage.clickOurTariffs();

        TariffsPage tariffsPage = new TariffsPage(driver,test);
        tariffsPage.verifyPage();
        tariffsPage.clickCalculatePremiumButton();

        WebPage webPage = new WebPage(driver, test);
        webPage.click();
        test.log(Status.INFO, "Test Complete");


    }

   @AfterEach
    public void clear(){
        driver.resetApp();
    }


    @AfterAll
    public void end(){
        driver.closeApp();
    }
}
