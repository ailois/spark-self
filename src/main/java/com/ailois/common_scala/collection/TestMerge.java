package com.ailois.common_scala.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class TestMerge {

    private static final Logger logger = LoggerFactory.getLogger(TestMerge.class);

    public static void main(String[] args)
    {

        // create a HashMap and add some values
        HashMap<Integer, String>
                map1 = new HashMap<>();
        map1.put(1, "L");
        map1.put(2, "M");
        map1.put(3, "N");
        map1.put(4, "H");

        HashMap<Integer, String>
                map2 = new HashMap<>();
        map2.put(1, "B");
        map2.put(2, "G");
        map2.put(3, "R");
        map2.put(5, "E");

        // print map details
        logger.info("HashMap1: {}", map1);
        logger.info("HashMap2: {}", map2);


        // provide value for new key which is absent
        // using computeIfAbsent method
        map2.forEach(
                (key, value)
                        -> map1.merge(
                        key,
                        value,
                        (v1, v2)
                                -> v1.equalsIgnoreCase(v2)
                                ? v1
                                : v1 + " and " + v2));
        // print new mapping
        logger.info("New HashMap: {}", map1);
    }

}
