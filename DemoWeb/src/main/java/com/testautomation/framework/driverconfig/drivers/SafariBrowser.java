package com.testautomation.framework.driverconfig.drivers;


import com.testautomation.framework.base.DataConfig;
import com.testautomation.framework.driverconfig.BaseWebDriver;
import com.testautomation.framework.utils.Prop;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SafariBrowser extends BaseWebDriver<RemoteWebDriver, DesiredCapabilities, SafariBrowser> {

    private DataConfig dataConfig=null;
    public SafariBrowser(DataConfig dataConfig) {
        this.dataConfig = dataConfig;
    }
  @Override
  protected SafariBrowser setDriverPath() {

    return null;
  }

  @Override
  public DesiredCapabilities getDefaultOptions() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        SafariOptions safarioptions = new SafariOptions();
        //    options.setUseCleanSession(true);
        capabilities = DesiredCapabilities.safari();
        capabilities.setCapability(SafariOptions.CAPABILITY, safarioptions);
        capabilities.setBrowserName(DesiredCapabilities.safari().getBrowserName());
        capabilities.setPlatform(Platform.WINDOWS);

        return capabilities;
    }

  protected DesiredCapabilities getOptions(DesiredCapabilities capabilities) {
    return capabilities == null ? getDefaultOptions() : capabilities;
  }

  @Override
  public RemoteWebDriver buildWebDriver(DesiredCapabilities options)
      throws MalformedURLException {
      dataConfig.safariDriver = setWebDriverManage(new RemoteWebDriver(new URL(Prop.getProperty("localGridHub")), getOptions(options)));
      return dataConfig.safariDriver;
  }

}
