package com.ailois.math;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMath {

    private static final Logger logger = LoggerFactory.getLogger(TestMath.class);

    public static void main(String[] args) {
        logger.info("{}", Math.pow(5, -1) > Math.exp(1));
    }

}
