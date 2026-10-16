package utilities;

import com.aventstack.extentreports.ExtentTest;

public class ReporterUtil {
    private ExtentTest test;

    public ReporterUtil(ExtentTest test) {
        this.test = test;
    }

    public void log(String message) {
        System.out.println(message);   // console
        test.info(message);            // extent
    }
}


