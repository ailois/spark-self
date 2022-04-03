package com.ailois.common_scala.date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TestZone {

    public static void main(String[] args) {//NOSONAR

        String zone = "Asia/Shanghai";
//        String zone = "UTC";//NOSONAR
        ZoneId zoneId = ZoneId.of(zone);//获取时区Id
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(zoneId);//以1970-01-01的形式格式化时间;//NOSONAR
        String formatStr = formatter.format(ZonedDateTime.now());//格式化当前的时间为字符串
        System.out.println(formatStr);//NOSONAR

    }

    private static void printZoneDefault() {//NOSONAR
        ZoneId zone = ZoneId.systemDefault();
        System.out.println(zone);//NOSONAR
    }

    private static void getCurrentDate(){//NOSONAR
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDateTime localDate = instant.atZone(defaultZoneId).toLocalDateTime();
        System.out.println(localDate);//NOSONAR
    }


}
