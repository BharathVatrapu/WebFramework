package com.testautomation.framework.driverconfig;

import java.net.MalformedURLException;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface WebDriver<S extends RemoteWebDriver,T extends Capabilities> {

  S buildWebDriver(T options) throws MalformedURLException;

}
