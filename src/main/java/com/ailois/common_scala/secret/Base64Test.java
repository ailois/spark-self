package com.ailois.common_scala.secret;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Test {

    private static final Logger logger = LoggerFactory.getLogger(Base64Test.class);

    public static void main(String[] args) {
        String baseEncode = baseEncode("休闲");
        logger.info("{}", baseEncode);
        String encodedText = baseDecode("NjM3OQ==");
        logger.info("{}", encodedText);
    }

    public static String baseEncode(String text) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] textByte = text.getBytes(StandardCharsets.UTF_8);
        return encoder.encodeToString(textByte);
    }

    public static String baseDecode(String encodedText) {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(encodedText), StandardCharsets.UTF_8);
    }

}
