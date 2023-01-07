package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReportObject(){
        String path=System.getProperty("user.dir")+"\\reports\\report.html";
        ExtentSparkReporter reporter=new ExtentSparkReporter(path);
        reporter.config().setDocumentTitle("Test Results");
        reporter.config().setReportName("API Automation Test Results");
        ExtentReports extent=new ExtentReports();
        extent.attachReporter(reporter);
        return extent;
    }
}
