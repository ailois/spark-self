package com.ailois.common_scala.secret;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnicodeTest {

    private static final Logger logger = LoggerFactory.getLogger(UnicodeTest.class);

    public static void main(String[] args) {
        String str1 = "\\u51f9\\u51f8\\u4e16\\u754c";
        String s = decodeUnicode(str1);
        logger.info("{}", s);

        String str2 = "\u51f9\u51f8\u4e16\u754c";
        logger.info("{}", str2);
    }

    public static String decodeUnicode(String dataStr) {
        int start = 0;
        int end;
        StringBuilder buffer = new StringBuilder();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr;
            if (end == -1) {
                charStr = dataStr.substring(start + 2);
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16);
            buffer.append(letter);
            start = end;
        }
        return buffer.toString();
    }

}
