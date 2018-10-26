package com.testautomation.framework.base;

import com.testautomation.framework.driverconfig.ConfigDriver;
import com.testautomation.framework.utils.DateAndTime;
import com.testautomation.framework.utils.ExtentManager;
import com.testautomation.framework.utils.Generic;
import com.testautomation.framework.utils.JsonUtils;
import com.testautomation.framework.utils.Log;
import com.testautomation.framework.utils.Prop;
import com.testautomation.framework.utils.ScreenshotGenarator;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class ConfigBase {

    public DataConfig dataConfig=new DataConfig();
    public ConfigDriver configDriver = null;
    public ScreenshotGenarator screenshotGenarator = null;
    public ExtentManager extentManager = new ExtentManager(dataConfig);
    public JsonUtils jsonUtils= new JsonUtils();
    public Generic generic=null;
    HashMap<String, String> enviornmetnHashmap = null;
    public boolean testExecute;


    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(){
        try {


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @BeforeTest(alwaysRun = true)
    public void extentConfig(ITestContext context) {
        String reportName = null;
        String groupName = null;
        dataConfig.suiteXmlName = context.getSuite().getName();
       // groupName=generic.getSuiteXmlGroupName(context.getIncludedGroups());
        reportName = dataConfig.suiteXmlName + "_[" + DateAndTime.getTime() +"]_"+DateAndTime.getDate()+".html";
        extentManager.createReportFile(reportName);
    }

    @Parameters({"environment","platform","service"})
    @BeforeClass(alwaysRun = true)
    public void initSetup(String environment, String platformType, String service){
        if(System.getProperty("environment")!=null) {
            dataConfig.testEnvironment = System.getProperty("environment").toLowerCase();
            dataConfig.testservice = System.getProperty("service").toLowerCase();
            dataConfig.testPlatform = System.getProperty("platform").toLowerCase();
        }else {
            dataConfig.testEnvironment = environment.toLowerCase();
            dataConfig.testservice = service.toLowerCase();
            dataConfig.testPlatform = platformType.toLowerCase();
        }
    }

    @Parameters({"browser","network","mobileDevice","appName","udid","deviceName","platformName","platformVersion", "manufacturer"})
    @BeforeMethod(alwaysRun = true)
    public void initWebBrowserSetup(Method testMethod,@Optional("optional") String browser,@Optional("optional")String network,@Optional("optional")String mobileDevice,@Optional("optional")String appName,@Optional("optional")String udid,@Optional("optional") String deviceName,@Optional("optional")String platformName,@Optional("optional")String platformVersion,@Optional("optional")String manufacturer) throws Exception {
        initClass();
        if(System.getProperty("browser")!=null) {
            dataConfig.testBrowser = System.getProperty("browser").toLowerCase();
        }else {
            dataConfig.testBrowser = browser.toLowerCase();
        }
        if(System.getProperty("mobileDevice")!=null) {
            dataConfig.testNetowk = System.getProperty("network").toLowerCase();
            dataConfig.testDeviceName = System.getProperty("mobileDevice").toLowerCase();
            dataConfig.testAppName = System.getProperty("appName").toLowerCase();
            if(System.getProperty("udid")!=null){
                dataConfig.mb_udid = System.getProperty("udid").toLowerCase();
                dataConfig.mb_deviceName = System.getProperty("deviceName").toLowerCase();
                dataConfig.mb_platformName = System.getProperty("platformName").toLowerCase();
                dataConfig.mb_platformVersion = System.getProperty("platformVersion").toLowerCase();
                dataConfig.mb_manufacturer = System.getProperty("manufacturer").toLowerCase();
            } else {
                if(dataConfig.testPlatform.equalsIgnoreCase("mobile")){
                    generic.readMobileCapabilities(mobileDevice);
                }
            }

        } else {
            dataConfig.testNetowk = network.toLowerCase();
            dataConfig.testDeviceName = mobileDevice.toLowerCase();
            dataConfig.testAppName = appName.toLowerCase();
            if(udid.equalsIgnoreCase("optional")) {
                if(dataConfig.testPlatform.equalsIgnoreCase("mobile")) {
                    generic.readMobileCapabilities(mobileDevice);
                }
            } else{
                dataConfig.mb_udid = udid.toLowerCase();
                dataConfig.mb_deviceName = deviceName.toLowerCase();
                dataConfig.mb_platformName = platformName.toLowerCase();
                dataConfig.mb_platformVersion = platformVersion.toLowerCase();
                dataConfig.mb_manufacturer = manufacturer.toLowerCase();
            }
        }
        try {
            dataConfig.testMethodName = testMethod.getName();
            testExecute = generic.testExecute();
            if(testExecute) {
                initDriver();
            }
            extentManager.createTest(dataConfig.testMethodName);
        } catch (Exception e){
            e.printStackTrace();
        }
        Log.info(dataConfig.testMethodName + " :: TestScript is Start");
    }

    @AfterMethod(alwaysRun = true)
    protected void afterMethod(ITestResult result) {

        for(String group:result.getMethod().getGroups()){
            extentManager.assignGroup(group);
        }
        try{
            dataConfig.driver.quit();
            extentManager.addfinalStatus(dataConfig.finalTestCaseStatus);
        } catch (Exception e){
            e.printStackTrace();
        }

        Log.info(dataConfig.testMethodName + " :: TestScript is End");
        Log.info("===================================================================");
    }

    public void initClass(){
        screenshotGenarator = new ScreenshotGenarator(dataConfig);
        generic = new Generic(dataConfig);
    }

    public void initDriver(){
        try {
            configDriver = new ConfigDriver(dataConfig);
            configDriver.initDriver();
            //  Set Objet Defination file path
            generic.getObjectDefFile();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {

        try{
            enviornmetnHashmap = new HashMap<String, String>();
            if(dataConfig.testPlatform.equalsIgnoreCase("desktop")){
                enviornmetnHashmap.put("OS",Generic.getCurretnPlatform().toString());
                enviornmetnHashmap.put("UserName",System.getProperty("user.name"));
                enviornmetnHashmap.put("Environment",dataConfig.testEnvironment.toUpperCase());
                enviornmetnHashmap.put("Platform",dataConfig.testPlatform.toUpperCase());
                enviornmetnHashmap.put("Browser",dataConfig.testBrowser.toUpperCase());
                extentManager.setSystemInfo(enviornmetnHashmap);
            } else {
                if(dataConfig.testAppName.equalsIgnoreCase("optional")){
                    if(dataConfig.mb_platformName.equalsIgnoreCase("android")){
                        dataConfig.testBrowser = "CHROME";
                    } if(dataConfig.mb_platformName.equalsIgnoreCase("ios")){
                        dataConfig.testBrowser = "SAFARI";
                    }
                    enviornmetnHashmap.put("OS",dataConfig.mb_platformName.toUpperCase());
                    enviornmetnHashmap.put("UserName",System.getProperty("user.name"));
                    enviornmetnHashmap.put("Environment",dataConfig.testEnvironment.toUpperCase());
                    enviornmetnHashmap.put("Platform",dataConfig.testPlatform.toUpperCase());
                    enviornmetnHashmap.put("Mobile Device",dataConfig.mb_deviceName.toUpperCase());
                    enviornmetnHashmap.put("Browser",dataConfig.testBrowser);
                    extentManager.setSystemInfo(enviornmetnHashmap);
                } else{
                    enviornmetnHashmap.put("OS",dataConfig.mb_platformName.toUpperCase());
                    enviornmetnHashmap.put("UserName",System.getProperty("user.name"));
                    enviornmetnHashmap.put("Environment",dataConfig.testEnvironment.toUpperCase());
                    enviornmetnHashmap.put("Platform",dataConfig.testPlatform.toUpperCase());
                    enviornmetnHashmap.put("Mobile Device",dataConfig.mb_deviceName.toUpperCase());
                    enviornmetnHashmap.put("App",dataConfig.testAppName);
                    extentManager.setSystemInfo(enviornmetnHashmap);
                }
            }


            extentManager.assignLog(generic.readFile(DataConfig.workDir+Prop.getProperty("Testomation.log.file")));
            extentManager.setHtmlConfig(dataConfig.suiteXmlName);
            extentManager.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
