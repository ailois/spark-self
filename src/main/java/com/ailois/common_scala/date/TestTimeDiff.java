package com.ailois.common_scala.date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestTimeDiff {

    private static final Logger logger = LoggerFactory.getLogger(TestTimeDiff.class);
    public static void main(String[] args) {

        Long startTime = 1577698711767L;
        Long endTime = 1577698712012L;

        logger.info("endTime - startTime = {}", endTime - startTime);

    }
}
