package com.demoproject.base;



import com.aventstack.extentreports.Status;
import com.demoproject.pagemodel.web.HomePage;
import com.demoproject.pagemodel.web.SignInPage;
import com.testautomation.framework.base.ConfigBase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


public class TestBase extends ConfigBase {


    public HomePage homePage  = null;
    public SignInPage signInPage  = null;



    @BeforeMethod(alwaysRun = true)
    public void initTestBase(){
        initilizeClasses();
    }



    public void verify(boolean condition, String message){
        dataConfig.stepNo = dataConfig.stepNo+1;
        if(condition){
            extentManager.addExecutionStep(Status.PASS,message);
        } else{
            message=message.replaceAll("is","is not");
            extentManager.addExecutionStep(Status.FAIL,message);
            dataConfig.finalTestCaseStatus = Status.FAIL;
        }

    }

    public String getTestDatavalue(String key){
        String value=null;
        value = jsonUtils.getValue(jsonUtils.parseStringToJsonObject(dataConfig.TEST_DATA_FILE_PATH),dataConfig.testMethodName,key);
        return value;
    }

    public void Assert(boolean condition, String message){
        Assert.assertTrue(condition);
    }


    public void initilizeClasses(){
        homePage = new HomePage(dataConfig);
        signInPage = new SignInPage(dataConfig);
    }
}
