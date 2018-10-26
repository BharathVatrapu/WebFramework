package com.testautomation.framework.driverconfig.drivers;


import com.testautomation.framework.base.DataConfig;
import com.testautomation.framework.driverconfig.BaseWebDriver;
import com.testautomation.framework.utils.Prop;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IE11Browser extends BaseWebDriver<RemoteWebDriver, DesiredCapabilities, IE11Browser> {

    private DataConfig dataConfig=null;
    public IE11Browser(DataConfig dataConfig) {
        this.dataConfig = dataConfig;
    }
  @Override
  protected IE11Browser setDriverPath() {
      System.setProperty("webdriver.chrome.driver", Prop.getProperty("ie.driver.file"));
    return this;
  }

  @Override
  public DesiredCapabilities getDefaultOptions() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities = DesiredCapabilities.internetExplorer();
        //capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        //capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, false);
        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
      capabilities.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
        capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);

        return capabilities;
    }

  protected DesiredCapabilities getOptions(DesiredCapabilities capabilities) {
    return capabilities == null ? getDefaultOptions() : capabilities;
  }

  @Override
  public RemoteWebDriver buildWebDriver(DesiredCapabilities options)
      throws MalformedURLException {
      dataConfig.internerExplorerDriver = setWebDriverManage(new RemoteWebDriver(new URL(Prop.getProperty("localGridHub")), getOptions(options)));
      return dataConfig.internerExplorerDriver;
  }

}
