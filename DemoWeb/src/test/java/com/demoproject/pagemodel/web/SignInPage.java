package com.demoproject.pagemodel.web;


import com.testautomation.framework.base.BasePage;
import com.testautomation.framework.base.DataConfig;
import com.testautomation.framework.utils.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends BasePage {
    //*********Page Variables*********
    String baseURL = "https://ngcp-qa1.safeway.com/CMS/account/login/";
    private DataConfig dataConfig=null;

    //*********Constructor*********
    public SignInPage(DataConfig dataConfig) {
        super(dataConfig.driver);
        this.dataConfig=dataConfig;
        PageFactory.initElements(dataConfig.driver,this);
    }

    //********* Sign In Page Web Elements by using Page Factory*********

    @FindBy(how = How.ID, using = "input-email")
    @CacheLookup
    public WebElement inputEmailAddress;
    @FindBy(how = How.ID, using = "password-password")
    @CacheLookup
    public WebElement inputPassword;
    @FindBy(how = How.ID, using = "create-account-btn")
    @CacheLookup
    public WebElement btnSignIn;

    @FindBy(how = How.XPATH, using = "//ng-form[@name='emailFieldForm']//p")
    @CacheLookup
    public WebElement errorMessageUsername;

    @FindBy(how = How.XPATH, using = "//ng-form[@name='passwordFieldForm']//p")
    @CacheLookup
    public WebElement errorMessagePassword;

    //*********Page Methods*********

    public void gotoSignInPage(){
        Log.startLog("'gotoHomePage'");
        navigateToURL(baseURL);
        Log.info("Navigated URL is :"+baseURL);
        Log.endLog("gotoHomePage");

    }
    public void userLogin (String emailAddress, String password){
        //Enter Username(Email)
        writeText(inputEmailAddress,emailAddress);
        //Enter Password(password)
        writeText(inputPassword,password);
        //click on SignIn Button once enabled
        //wait.until(ExpectedConditions.elementToBeClickable(btnSignIn));
        click(btnSignIn);
    }

    //Verify Email Address Error Message
    public boolean verifyLoginUserName (String expectedText) {
        boolean returnStatus=true;
        click(btnSignIn);
        if(errorMessageUsername.getText().equalsIgnoreCase(expectedText)){
            returnStatus=false;
        }
        return returnStatus;
    }

    //Verify Password Error Message
    public boolean verifyLoginPassword (String expectedText) {
        boolean returnStatus=true;
        click(btnSignIn);
        if(errorMessagePassword.getText().equalsIgnoreCase(expectedText)){
            returnStatus=false;
        }
        return returnStatus;
    }
}
