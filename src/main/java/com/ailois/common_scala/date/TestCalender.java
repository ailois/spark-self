package com.ailois.common_scala.date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestCalender {

    private static final Logger logger = LoggerFactory.getLogger(TestCalender.class);

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String format = dateFormat.format(new Date());
        logger.info("{}", format);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -7);
        Date time = calendar.getTime();
        time = dateFormat.parse(dateFormat.format(time));
        long time1 = time.getTime();
        logger.info("{}", time1);

        String date = dateFormat.format(calendar.getTime());
        logger.info("{}", date);

    }

}
