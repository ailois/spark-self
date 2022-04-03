package com.ailois.ip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestIpTransform {

    private static final Logger logger = LoggerFactory.getLogger(TestIpTransform.class);

    public static void main(String[] args) {

        Map<Long, List<String>> test = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("test");
        test.put(1L, list);
        logger.info("{}", test);
        logger.info("{}", test.get(2L));

    }


}
