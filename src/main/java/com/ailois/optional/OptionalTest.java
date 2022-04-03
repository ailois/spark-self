package com.ailois.optional;

import org.apache.spark.api.java.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class OptionalTest {

    private static final Logger logger = LoggerFactory.getLogger(OptionalTest.class);

    public static void main(String[] args) {

        HashMap<Optional<String>, String> map1 = new HashMap<>();
        map1.put(Optional.of("test"), "test1");
        logger.info("====map1====");
        logger.info("get optional key: {}", map1.get(Optional.of("test")));


        logger.info("");
        logger.info("====map2====");
        HashMap<String, Optional<String>> map2 = new HashMap<>();
        map2.put("map2_test", Optional.of("opt_map2_test"));
        logger.info("not exist key, map2: {}", map2.get("map2"));
        logger.info("exist key, map2_test: {}", map2.get("map2_test"));
        logger.info("not exist key, use get or default, map2: {}", map2.getOrDefault("map2", Optional.empty()));

    }

}
