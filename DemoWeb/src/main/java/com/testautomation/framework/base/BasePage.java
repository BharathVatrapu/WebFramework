package com.testautomation.framework.base;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends PageGenerator {

    public BasePage(RemoteWebDriver driver) {
        super(driver);
    }

    //If we need we can use custom wait in BasePage and all page classes
    WebDriverWait wait = new WebDriverWait(driver,20);

    public void navigateToURL(String URL) {
        try {
            driver.get(URL);
            if (!driver.getCurrentUrl().equals(URL)) {
                driver.get(URL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public boolean verifyPageUrl(String Url){
        boolean returnStatus = true;
        String getpageUrl=getURL();
        if(Url.contains("https")){
            Url = Url.replaceAll("https://","");
        } else {
            Url = Url.replaceAll("http://","");
        }
        getpageUrl=getpageUrl.toLowerCase().trim();
        Url=Url.toLowerCase().trim();
        if(!getpageUrl.contains(Url)){
            returnStatus = false;
        }
        return returnStatus;
    }
    public String getURL()  {
        return driver.getCurrentUrl();
    }


    //Click Method
    public <T> void click (T elementAttr) {
        if(elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).click();
        } else {
            ((WebElement) elementAttr).click();
        }
    }

    //Write Text by using JAVA Generics (You can use both By or Webelement)
    public <T> void writeText (T elementAttr, String text) {
        if(elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).sendKeys(text);
        } else {
            ((WebElement) elementAttr).sendKeys(text);
        }
    }

    //Read Text by using JAVA Generics (You can use both By or Webelement)
    public <T> String readText (T elementAttr) {
        if(elementAttr.getClass().getName().contains("By")) {
            return driver.findElement((By) elementAttr).getText();
        } else {
            return ((WebElement) elementAttr).getText();
        }
    }

    //Close popup if exists
    public void handlePopup (By by) throws InterruptedException {
        List<WebElement> popup = driver.findElements(by);
        if(!popup.isEmpty()){
            popup.get(0).click();
            Thread.sleep(200);
        }
    }
}
