package com.ailois.common_scala.date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.TimeZone;

public class TestDateFormatUtils {

    private static final Logger logger = LoggerFactory.getLogger(TestDateFormatUtils.class);

    public static void main(String[] args) {

        String str = "135055815725814700463078373685049526216";
        logger.info("{}", str.length());

        logger.info("{}", getIntervalDays());

    }

    private static String getIntervalDays() {
        return DateFormatUtils.format(DateUtils.addDays(new Date(), -7), "yyyy-MM-dd 00:00:00",
                TimeZone.getTimeZone("Asian/Shanghai"));
    }


}
