package com.ailois.date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.IntStream;

public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    private static final String[] FORMAT = new String[]{"yyyy-MM-dd"};

    public static void main(String[] args) {
        String startDate = "2020-10-31";
        String endDate = "2020-11-13";
        logger.info("{}", generateDayList(startDate, endDate));
    }

    private static Set<String> generateDayList(String startDate, String endDate) {
        int dateDiff = getDateDiff(endDate, startDate);
        Set<String> dates = new HashSet<>();
        dates.add(startDate);
        dates.add(endDate);
        IntStream.range(0, dateDiff).forEach(i -> dates.add(getDateBefore(startDate, -1 * i)));
        return dates;
    }

    public static String getDateBefore(String date, int amount) {
        try {
            return DateFormatUtils.format(org.apache.commons.lang3.time.DateUtils.addDays(org.apache.commons.lang3.time.DateUtils.parseDate(date, FORMAT), -1 * amount),
                    "yyyy-MM-dd", TimeZone.getTimeZone("Asia/Shanghai"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static int getDateDiff(String date, String dateVs) {

        try {
            return (int) ((org.apache.commons.lang3.time.DateUtils.parseDate(date, FORMAT).getTime() -
                    org.apache.commons.lang3.time.DateUtils.parseDate(dateVs, FORMAT).getTime()) / (60 * 60 * 24 * 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
