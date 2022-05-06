package com.ailois.common_scala.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class TestFlatMapStream {

    private static final Logger logger = LoggerFactory.getLogger(TestFlatMapStream.class);

    public static void main(String[] args) {
        List<HashMap<String, Integer>> list = new ArrayList<>();
        HashMap<String, Integer> map1 = new HashMap<>();
        map1.put("key", 1);
        HashMap<String, Integer> map2 = new HashMap<>();
        map2.put("key", 2);
        list.add(map1);
        list.add(map2);
        Map<String, Integer> collect = list.stream().map(HashMap::entrySet)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum));
        logger.info("{}", collect);
    }

}
