package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class Report {
    ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;

    @BeforeAll
    public void reportSetup(){
        htmlReporter = new ExtentHtmlReporter("Ottonova_Report.html");

        // create ExtentReports and attach reporter(s)
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

    }
    @AfterAll
    public void reportClose(){
    extent.flush();
    }
}
