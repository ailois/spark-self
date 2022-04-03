package com.ailois.common_scala.date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

@SuppressWarnings("all")
public class TestDateAddDays {

    public static void main(String[] args) {
        System.out.println(getDateDiff("2020-10-27", "2020-10-24")); //NOSONAR
    }

    private static String getDateBefore(Integer days, String format) { //NOSOANR
        return DateFormatUtils.format(DateUtils.addDays(new Date(), -1 * days),
                format, TimeZone.getTimeZone("Asia/Shanghai"));
    }

    public static String getBeforeDate(int days) {
        return DateFormatUtils.format(DateUtils.addDays(new Date(), -1 * days),
                "yyyy-MM-dd", TimeZone.getTimeZone("Asia/Shanghai"));
    }

    private static int getDateDiff(String date, String dateVs) { //NOSONAR
        String[] format = {"yyyy-MM-dd"};
        try {
            return (int) ((DateUtils.parseDate(dateVs, format).getTime() - DateUtils.parseDate(date, format).getTime()) / (60 * 60 * 24 * 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
