package com.ailois.math;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TestDecimalFormat {

    private static final Logger logger = LoggerFactory.getLogger(TestDecimalFormat.class);

    public static void main(String[] args) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##%");
        String format = decimalFormat.format(1.0 / 3.0);
        logger.info("{}", format);

        Date from = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        logger.info("{}", from);
        logger.info("{}", getStartOfToday());
    }

    private static String getStartOfToday() {
        return LocalDate.now().atStartOfDay(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
