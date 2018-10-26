package com.testautomation.framework.driverconfig.drivers;


import com.testautomation.framework.base.DataConfig;
import com.testautomation.framework.driverconfig.BaseWebDriver;
import com.testautomation.framework.utils.Prop;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirefoxBrowser extends BaseWebDriver<RemoteWebDriver, DesiredCapabilities, FirefoxBrowser> {

    private DataConfig dataConfig=null;
    public FirefoxBrowser(DataConfig dataConfig) {
        this.dataConfig = dataConfig;
    }
  @Override
  protected FirefoxBrowser setDriverPath() {
      System.setProperty("webdriver.gecko.driver", Prop.getProperty("firefox.driver.file"));
    return this;
  }

  @Override
  public DesiredCapabilities getDefaultOptions() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    if(dataConfig.testservice.equalsIgnoreCase("headless")){
       FirefoxBinary firefoxBinary = new FirefoxBinary();
       firefoxBinary.addCommandLineOptions("--headless");
       FirefoxOptions firefoxOptions = new FirefoxOptions();
       firefoxOptions.setBinary(firefoxBinary);
       capabilities = DesiredCapabilities.firefox();
       capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
    } else {
       capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//       capabilities.setBrowserName("firefox");
        capabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
    }
      return capabilities;
   }

  protected DesiredCapabilities getOptions(DesiredCapabilities capabilities) {
    return capabilities == null ? getDefaultOptions() : capabilities;
  }

  @Override
  public RemoteWebDriver buildWebDriver(DesiredCapabilities options)
      throws MalformedURLException {
      dataConfig.firefoxDriver = setWebDriverManage(new RemoteWebDriver(new URL(Prop.getProperty("localGridHub")), getOptions(options)));
      return dataConfig.firefoxDriver;
  }

}
