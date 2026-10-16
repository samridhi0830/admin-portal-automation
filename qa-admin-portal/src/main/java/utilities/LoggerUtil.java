package utilities;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.aventstack.extentreports.ExtentTest;

import Listeners.ExtentReportListener;
import Listeners.ExtentReporterListener;

public class LoggerUtil {

    private static final Logger log = LogManager.getLogger(LoggerUtil.class);
   
    
    private static ExtentTest getExtentTest() {
    	return ExtentReporterListener.getExtentTest(); // <-- fetch from listener }
    }

    

    public static void info(String message) {
        log.info(message);
        

        ExtentTest extentTest = getExtentTest();
        if (extentTest == null) {
            System.out.println("ExtentTest is NULL in LoggerUtil");
        }
        if (extentTest != null) 
        	extentTest.info(message);
    }

    public static void pass(String message) {
        log.info("PASS: " + message);
        ExtentTest extentTest = getExtentTest();
        if (extentTest != null) 
        	extentTest.pass(message);
    }

    public static void fail(String message) {
        log.error("FAIL: " + message);
        ExtentTest extentTest = getExtentTest();
        if (extentTest != null) 
        	extentTest.fail(message);
    }
}




