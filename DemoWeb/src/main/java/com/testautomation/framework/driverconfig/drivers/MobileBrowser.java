package com.testautomation.framework.driverconfig.drivers;


import com.testautomation.framework.base.DataConfig;
import com.testautomation.framework.driverconfig.BaseWebDriver;
import com.testautomation.framework.utils.Prop;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MobileBrowser extends BaseWebDriver<RemoteWebDriver, DesiredCapabilities, MobileBrowser> {
    private DataConfig dataConfig=null;
    public MobileBrowser(DataConfig dataConfig) {
        this.dataConfig = dataConfig;
    }
  @Override
  protected MobileBrowser setDriverPath() {
    return null;
  }

  @Override
  public DesiredCapabilities getDefaultOptions() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (dataConfig.testNetowk.equalsIgnoreCase("cloud")) {
            if(Prop.getProperty("mobile.cloud.env").equalsIgnoreCase("public")){
                capabilities.setCapability("user", Prop.getProperty("mobile.cloud.public.userid"));
                capabilities.setCapability("password", Prop.getProperty("mobile.cloud.public.password"));
            } else{
                capabilities.setCapability("user", Prop.getProperty("mobile.cloud.private.userid"));
                capabilities.setCapability("password", Prop.getProperty("mobile.cloud.private.password"));
            }

        }

        if (dataConfig.mb_platformName.equalsIgnoreCase("Android")) {
            //capabilities = DesiredCapabilities.android();
            capabilities.setCapability(MobileCapabilityType.UDID, dataConfig.mb_udid);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, dataConfig.mb_deviceName);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, dataConfig.mb_platformVersion);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, dataConfig.mb_platformName);
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 9999);
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
        } else if (dataConfig.mb_platformName.equalsIgnoreCase("ios")) {
            capabilities.setCapability(MobileCapabilityType.UDID, dataConfig.mb_udid);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, dataConfig.mb_deviceName);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, dataConfig.mb_platformVersion);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, dataConfig.mb_platformName);
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 9999);
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "safari");
        }
        return capabilities;
    }

  protected DesiredCapabilities getOptions(DesiredCapabilities capabilities) {
    return capabilities == null ? getDefaultOptions() : capabilities;
  }

  @Override
  public RemoteWebDriver buildWebDriver(DesiredCapabilities options)
      throws MalformedURLException {
      if (StringUtils.equalsIgnoreCase(dataConfig.testNetowk, "cloud")) {
          if (StringUtils.equalsIgnoreCase(Prop.getProperty("mobile.cloud.env"), "public")) {
              dataConfig.mobileWebDriver = setWebDriverManage(new RemoteWebDriver(new URL(Prop.getProperty("mobile.cloud.public.url")), getOptions(options)));
          } else {
              dataConfig.mobileWebDriver = setWebDriverManage(new RemoteWebDriver(new URL(Prop.getProperty("mobile.cloud.private.url")), getOptions(options)));
          }
      } else {
          dataConfig.mobileWebDriver = setWebDriverManage(new RemoteWebDriver(new URL(Prop.getProperty("localGridHub")), getOptions(options)));

      }
      return dataConfig.mobileWebDriver;
  }

}
