package com.ailois.common_scala.date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDataFormat {

    private static final Logger logger = LoggerFactory.getLogger(TestDataFormat.class);
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
        Date date = new Date(1586670586000L);
        String format = dateFormat.format(date);
        logger.info("{}", format);

        String testDate = "2021-02-18 15:37:00";
        long time = DateUtils.parseDate(testDate, FORMAT, "Asia/Shanghai").getTime();
        long time2 = DateUtils.parseDate(testDate, FORMAT, "UTC").getTime();
        logger.info("time: {}", time);
        logger.info("time2: {}", time2);

    }



}
