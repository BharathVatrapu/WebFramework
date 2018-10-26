package com.demoproject.constants;

public enum module {

    Module1("Module1"),

    RSS_REGISTRATION("Registration"),
    SignIn("SignIn"),
    RSS_MYACCOUNT("MyAccount"),

    ECP_BOXTOP("Boxtop"),
    ECP_CONTACTUS("ContactUs");

    private String moduleName;

    private module(String moduleName) {
        this.setModuleName(moduleName);
    }

    public String getModuleName() {
        return this.moduleName;
    }

    private void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
