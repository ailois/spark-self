package com.ailois.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class DoubleTest {

    private static final Logger logger = LoggerFactory.getLogger(DoubleTest.class);

    public static void main(String[] args) {
        double naN = Double.NaN;
        try {
            BigDecimal bigDecimal = BigDecimal.valueOf(naN);
            logger.info("{}", bigDecimal);
        } catch (Exception e) {
            logger.error("can not cast Double.Nan to BigDecimal, {}", e.getMessage());
        }
    }

}
