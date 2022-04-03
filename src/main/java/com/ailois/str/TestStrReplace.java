package com.ailois.str;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestStrReplace {

    private static final Logger logger = LoggerFactory.getLogger(TestStrReplace.class);

    public static void main(String[] args) {

        String a = "adasd_dasd";
        logger.info(a);
        String b = "adasd";

        String a1 = b.split("_")[0];
        String a2 = b.replaceAll(a1+"[_]", "");
        logger.info(a1);
        logger.info(a2);


        String test2 = "[cb744b38f66b1a5d]";
        String subTest2 = test2.substring(1, test2.length() - 1);
        logger.info("{}", subTest2);
        String replace = test2.replace("[", "").replace("]", "");
        logger.info("{}", replace);
        String replaceAll = test2.replaceAll("[\\[\\]]", "");
        logger.info("{}", replaceAll);

    }

}
