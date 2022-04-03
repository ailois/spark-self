package com.ailois.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    private static final Properties props;
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    static {
        String fileName = "devices.properties";
        props = new Properties();
        try {
            InputStream in = new BufferedInputStream (new FileInputStream(fileName));
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        if (null == key) {
            return null;
        }
        String phoneName = key.split("_")[0];
        String model = key.replace("_", " ");
        String transTitle= props.getProperty(model);
        String result;
        if (null == transTitle || "".equals(transTitle)) {
            result = key;
        } else {
            result = phoneName + "_" + transTitle;
        }
        return result.trim();
    }

    public static void main(String[] args) {
        logger.info("{}", getProperty("OPPO_OPPO A73"));
    }



}
