package com.testautomation.framework.driverconfig;

import io.appium.java_client.AppiumDriver;
import java.net.MalformedURLException;
import org.openqa.selenium.Capabilities;

public interface MobileDriver<S extends AppiumDriver,T extends Capabilities> {

  S buildMobileDriver(T options) throws MalformedURLException;

}
