package com.testautomation.framework.driverconfig.drivers;


import com.testautomation.framework.base.DataConfig;
import com.testautomation.framework.driverconfig.BaseMobileDriver;
import com.testautomation.framework.utils.Prop;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IOS extends BaseMobileDriver<AppiumDriver, DesiredCapabilities, IOS> {
    private DataConfig dataConfig=null;
    public IOS(DataConfig dataConfig) {
        this.dataConfig = dataConfig;
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
        capabilities.setCapability(MobileCapabilityType.UDID, dataConfig.mb_udid);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, dataConfig.mb_deviceName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, dataConfig.mb_platformName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, dataConfig.mb_platformVersion);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, dataConfig.testDeviceName);
        capabilities.setCapability("bundleId", dataConfig.testAppName);

        capabilities.setCapability("newCommandTimeout", 9999);
        return capabilities;
    }

  protected DesiredCapabilities getOptions(DesiredCapabilities capabilities) {
    return capabilities == null ? getDefaultOptions() : capabilities;
  }

  @Override
  public AppiumDriver buildMobileDriver(DesiredCapabilities options)
      throws MalformedURLException {
         if (StringUtils.equalsIgnoreCase(dataConfig.testNetowk, "cloud")) {
              if (StringUtils.equalsIgnoreCase(Prop.getProperty("mobile.cloud.env"), "public")) {
                  dataConfig.iOSDriver = setMobileAppDriverManage(new IOSDriver(new URL(Prop.getProperty("mobile.cloud.public.url")), getOptions(options)));
              } else {
                  dataConfig.iOSDriver = setMobileAppDriverManage(new IOSDriver(new URL(Prop.getProperty("mobile.cloud.private.url")), getOptions(options)));
              }
          } else {
              dataConfig.iOSDriver = setMobileAppDriverManage(new IOSDriver(new URL(Prop.getProperty("localGridHub")), getOptions(options)));

          }
      return dataConfig.iOSDriver;
  }

}
