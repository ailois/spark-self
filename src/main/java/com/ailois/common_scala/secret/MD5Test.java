package com.ailois.common_scala.secret;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Test {

    private static final Logger logger = LoggerFactory.getLogger(MD5Test.class);

    public static void main(String[] args) {
        String test = encryptToMD5("毕研诚");
        logger.info("{}", test);

    }

    public static String encryptToMD5(String str) {
        return DigestUtils.md5Hex(str);
    }

}
