package com.ailois.common_scala.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class TestListToDropNull {

    private static final Logger logger = LoggerFactory.getLogger(TestListToDropNull.class);

    public static void main(String[] args) {

        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add(null);
        list.add(null);
        list.add("111");
        list.add("222");
        List<String> list1 = new ArrayList<>();
        list1.add(null);
        list1.add(null);
        list1.add("222");
        map.put("a", list);
        map.put("b", list1);

        List<String> collect = list.parallelStream().filter(Objects::nonNull).collect(Collectors.toList());

        logger.info("collect: {}", collect);
        logger.info("map: {}", map);

    }

}
