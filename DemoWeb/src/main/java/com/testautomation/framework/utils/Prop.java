package com.testautomation.framework.utils;

import com.testautomation.framework.base.DataConfig;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Prop {

    /**  Method is used for get property value from framework properites **/
    public static String getProperty(String propKey){
        String propValue=null;
        Properties prop = new Properties();
        InputStream input = null;
        try{
            input = new FileInputStream(DataConfig.PROPERTY_FILE_PATH);
            // load a properties file
            prop.load(input);
            // get the property value
            propValue = prop.getProperty(propKey);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return propValue;
    }
}
