package com.testautomation.framework.driverconfig.drivers;


import com.testautomation.framework.base.DataConfig;
import com.testautomation.framework.driverconfig.BaseWebDriver;
import com.testautomation.framework.utils.Prop;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeBrowser extends BaseWebDriver<RemoteWebDriver, DesiredCapabilities, ChromeBrowser> {

    private DataConfig dataConfig=null;
    public ChromeBrowser(DataConfig dataConfig) {
        this.dataConfig = dataConfig;
    }

  @Override
  protected ChromeBrowser setDriverPath() {
      System.setProperty("webdriver.chrome.driver", Prop.getProperty("chrome.driver.file"));

    return this;
  }

  @Override
  public DesiredCapabilities getDefaultOptions() {

    DesiredCapabilities capabilities = new DesiredCapabilities();
    if(dataConfig.testservice.equalsIgnoreCase("headless")){
        //  HEADLESS
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("window-size=1200x600");
        capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
    } else {
        // CHROME BROWSER
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        ChromeOptions chromeoptions = new ChromeOptions();
        chromeoptions.addArguments("test-type");
        chromeoptions.addArguments("disable-popup-blocking");
        capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeoptions);
//        capabilities.setBrowserName("chrome");
        capabilities.setBrowserName(DesiredCapabilities.chrome().getBrowserName());

    }
    return capabilities;
    }

  protected DesiredCapabilities getOptions(DesiredCapabilities capabilities) {
    return capabilities == null ? getDefaultOptions() : capabilities;
  }

  @Override
  public RemoteWebDriver buildWebDriver(DesiredCapabilities options)
      throws MalformedURLException {
      dataConfig.chromeDriver =  setWebDriverManage(new RemoteWebDriver(new URL(Prop.getProperty("localGridHub")), getOptions(options)));
      return dataConfig.chromeDriver;
  }

}
