package com.testautomation.framework.driverconfig;


import com.testautomation.framework.base.DataConfig;
import com.testautomation.framework.driverconfig.drivers.Android;
import com.testautomation.framework.driverconfig.drivers.ChromeBrowser;
import com.testautomation.framework.driverconfig.drivers.EdgeBrowser;
import com.testautomation.framework.driverconfig.drivers.FirefoxBrowser;
import com.testautomation.framework.driverconfig.drivers.IE11Browser;
import com.testautomation.framework.driverconfig.drivers.IOS;
import com.testautomation.framework.driverconfig.drivers.MobileBrowser;
import com.testautomation.framework.driverconfig.drivers.SafariBrowser;
import io.appium.java_client.AppiumDriver;
import java.net.MalformedURLException;
import javax.annotation.Nullable;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ConfigDriver {

    private DataConfig dataConfig=null;
    public ConfigDriver(DataConfig dataConfig) {
        this.dataConfig = dataConfig;
    }

    public void initDriver() throws Exception {
        if(dataConfig.testPlatform.equalsIgnoreCase("desktop")){
            dataConfig.driver = getWebDriver(null);
        } else if(dataConfig.testPlatform.equalsIgnoreCase("mobile")) {
            if(dataConfig.testservice.equalsIgnoreCase("web")){
             dataConfig.driver =  getWebDriver(null);

            } else {
                dataConfig.driver = getMobileDriver(null);
            }
        }
        System.out.println("configDriver::"+dataConfig.driver);
    }
    public AppiumDriver getMobileDriver(@Nullable Capabilities capabilities)throws MalformedURLException {
        MobileDriver mobileDriver = null;
        switch (dataConfig.mb_platformName.toLowerCase()) {
            case "android":
                mobileDriver  = new Android(dataConfig);
                break;
            case "ios":
                mobileDriver  = new IOS(dataConfig);
                break;
        }
        return mobileDriver.buildMobileDriver(capabilities);
    }
    public RemoteWebDriver getWebDriver(@Nullable Capabilities capabilities)
            throws MalformedURLException {
        WebDriver webDriver = null;

        switch (dataConfig.testBrowser) {
            case "firefox":
            case "ff":
                webDriver = new FirefoxBrowser(dataConfig);
                break;
            case "chrome":
                webDriver = new ChromeBrowser(dataConfig);
                break;
            case "ie":
            case "internet explorer":
            case "ie11":
                webDriver = new IE11Browser(dataConfig) ;
                break;
            case "safari":
                webDriver = new SafariBrowser(dataConfig) ;
                break;
            case "edge":
            case "microsoft edge":
                webDriver = new EdgeBrowser(dataConfig);
                break;
            default:
                webDriver = new MobileBrowser(dataConfig);
                break;

        }

        return webDriver.buildWebDriver(capabilities);
    }

}
