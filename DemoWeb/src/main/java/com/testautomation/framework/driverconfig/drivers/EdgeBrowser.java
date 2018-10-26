package com.testautomation.framework.driverconfig.drivers;


import com.testautomation.framework.base.DataConfig;
import com.testautomation.framework.driverconfig.BaseWebDriver;
import com.testautomation.framework.utils.Prop;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class EdgeBrowser extends BaseWebDriver<RemoteWebDriver, DesiredCapabilities, EdgeBrowser> {

    private DataConfig dataConfig=null;
    public EdgeBrowser(DataConfig dataConfig) {
        this.dataConfig = dataConfig;
    }

  @Override
  protected EdgeBrowser setDriverPath() {
      System.setProperty("webdriver.edge.driver", Prop.getProperty("edge.driver.file"));

    return this;
  }

  @Override
  public DesiredCapabilities getDefaultOptions() {

    DesiredCapabilities capabilities = new DesiredCapabilities();

        // driver = new EdgeDriver();
       // capabilities = DesiredCapabilities.edge();
        //    options.setUseCleanSession(true);
        capabilities.setBrowserName(DesiredCapabilities.edge().getBrowserName());
        // DesiredCapabilities capabilities = DesiredCapabilities.edge(); Tried as well
        capabilities.setCapability("acceptSslCerts", "true");
        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy("eager");


    return capabilities;
    }

  protected DesiredCapabilities getOptions(DesiredCapabilities capabilities) {
    return capabilities == null ? getDefaultOptions() : capabilities;
  }

  @Override
  public RemoteWebDriver buildWebDriver(DesiredCapabilities options)
      throws MalformedURLException {
      dataConfig.edgeDriver =  setWebDriverManage(new RemoteWebDriver(new URL(Prop.getProperty("localGridHub")), getOptions(options)));
      return dataConfig.edgeDriver;
  }

}
