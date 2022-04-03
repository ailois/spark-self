package com.ailois.common_scala.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class ListMapWordCount {

    private static final Logger logger = LoggerFactory.getLogger(ListMapWordCount.class);

    public static void main(String[] args) {

        List<Map<String, String>> list = new ArrayList<>();

        Map<String, String> map1 = new HashMap<>();
        map1.put("1", "1");
        map1.put("2", "2");
        map1.put("3", "3");
        map1.put("4", "4");
        list.add(map1);

        Map<String, String> map2 = new HashMap<>();
        map2.put("1", "2");
        map2.put("2", "3");
        map2.put("3", "4");
        map2.put("4", "5");
        list.add(map2);

        Map<String, String> map3 = new HashMap<>();
        map3.put("1", "3");
        map3.put("2", "4");
        map3.put("3", "5");
        map3.put("4", "6");
        list.add(map3);

        Map<String, Double> collect = list.stream().flatMap(map -> map.entrySet()
                .stream()).collect(groupingBy(Map.Entry::getKey)).entrySet()
                .stream().collect(toMap(Map.Entry::getKey, map -> map.getValue()
                        .stream().mapToDouble(x -> Double.parseDouble(x.getValue())).sum()))
                .entrySet().stream().collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

        logger.info("{}", collect);

    }

}
