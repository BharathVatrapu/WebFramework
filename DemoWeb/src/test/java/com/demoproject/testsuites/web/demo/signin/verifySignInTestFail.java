package com.demoproject.testsuites.web.demo.signin;


import com.aventstack.extentreports.Status;
import com.demoproject.base.TestBase;
import com.demoproject.constants.ProjectConstants;
import com.demoproject.constants.module;
import com.testautomation.framework.utils.Prop;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.MessageFormat;

public class verifySignInTestFail extends TestBase {


    @BeforeClass(alwaysRun = true)
    public void setTestData() {
        dataConfig.TEST_DATA_FILE_PATH = MessageFormat.format(Prop.getProperty("testdata.path"),System.getProperty("user.dir"),dataConfig.testEnvironment.toLowerCase(), module.SignIn,".json");
    }

    @Test(groups={"Registration"})
    public void verifySignInTestFail() throws Exception {
        if(testExecute) {
            //Variable Declarion
            // Step 1:
            // Validation
            homePage.gotoHomePage();
            verify(homePage.verifyHomePage(),"Home page is displayed");


            // Step 2:
            // Actions
            homePage.userSignIn("mercury", "mercury");
            // Validation
            verify(homePage.verifySuccessfullSignIn(), "SignIn is Successfully");

        } else{
            dataConfig.finalTestCaseStatus = Status.SKIP;
            throw new SkipException("Execute variable <> 'YES'. Skipping execution...");
        }

    }

}
