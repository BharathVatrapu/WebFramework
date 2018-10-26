package com.demoproject.pagemodel.web;

import com.testautomation.framework.base.BasePage;
import com.testautomation.framework.base.DataConfig;
import com.testautomation.framework.utils.Log;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    private DataConfig dataConfig=null;
    public HomePage(DataConfig dataConfig){
        super(dataConfig.driver);
        this.dataConfig=dataConfig;
        System.out.println("Driver::"+dataConfig.driver);
        PageFactory.initElements(dataConfig.driver,this);
    }

    // Variable Declaration
    List<String> objElements = new ArrayList<String>();
    String baseURL = "http://newtours.demoaut.com/";


    //********* HomePage Page Web Elements by using Page Factory*********

    @FindBy(how = How.XPATH, using = "//td/input[@name='userName']")
    @CacheLookup
    public WebElement inputUserName;
    @FindBy(how = How.XPATH, using = "//tr/td/input[@name='password']")
    @CacheLookup
    public WebElement inputPassword;
    @FindBy(how = How.NAME, using = "login")
    @CacheLookup
    public WebElement btnLogin;
    //#################################################################
    //#################################################################
    //##################        METHODS        #######################
    //#################################################################
    //#################################################################
    public void gotoHomePage(){
        Log.startLog("'gotoHomePage'");
        navigateToURL(baseURL);
        Log.info("Navigated URL is :"+baseURL);
        Log.endLog("gotoHomePage");

    }
    public boolean verifyHomePage(){
        dataConfig.stepDescription = "Navigated to Home Page. Verify the HomePage";
        return verifyPageUrl(baseURL);
    }


    public void userSignIn(String userId,String password){
        Log.startLog("'userSignIn'");
        Log.info("Signing in with login credentials: " + "User id: " + userId + ", " + "Password: " + password);
        try{
            writeText(inputUserName,userId);
            writeText(inputPassword,password);
            click(btnLogin);
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.endLog("'userSignIn'");
    }

    public boolean verifySuccessfullSignIn(){
        dataConfig.stepDescription = "Navigated to SignIn page. Verify the SignIn page";
        return verifyPageUrl(baseURL);
    }


}
