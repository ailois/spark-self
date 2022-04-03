package com.ailois.common_scala.date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;

public class TestAfterDate {

    private static final Logger logger = LoggerFactory.getLogger(TestAfterDate.class);

    public static void main(String[] args) {
        String endDate = "2020-07-09";
        try {
            Date date = DateUtils.parseDate(endDate, "yyyy-MM-dd");
            if (new Date().before(date) || DateUtils.isSameDay(new Date(), date)) {
                logger.info("=======");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
