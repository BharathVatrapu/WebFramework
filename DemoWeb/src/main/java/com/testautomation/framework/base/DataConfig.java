package com.testautomation.framework.base;

import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DataConfig {

    public static String workDir = System.getProperty("user.dir");

    // Testng params
    public String testEnvironment = null;
    public String testservice = null;
    public String testPlatform = null;

    public String testBrowser = null;

    public String testDeviceName = null;
    public String testAppName = null;
    public String testNetowk = null;



    // Mobile Capabilities Testng params
    public String mb_udid = null;
    public String mb_deviceName = null;
    public String mb_platformName = null;
    public String mb_platformVersion = null;
    public String mb_manufacturer = null;
    public String mb_appActivity = null;

    public String testMethodName = null;

    // Remote we driver
    public RemoteWebDriver mobileWebDriver = null;
    public RemoteWebDriver chromeDriver = null;
    public RemoteWebDriver firefoxDriver = null;
    public RemoteWebDriver internerExplorerDriver = null;
    public RemoteWebDriver safariDriver = null;
    public RemoteWebDriver edgeDriver = null;

    public AppiumDriver androidDriver = null;
    public AppiumDriver iOSDriver = null;

    public RemoteWebDriver driver = null;

    public String objectMapperDefPath = null;

    public static String PROPERTY_FILE_PATH = workDir+ "/src/test/resources/properties/configuration.properties";
    public String TEST_DATA_FILE_PATH = null;

    public Status finalTestCaseStatus = Status.PASS;

    public String suiteXmlName=null;

    public int stepNo =  0;
    public String stepDescription =  null;


}
