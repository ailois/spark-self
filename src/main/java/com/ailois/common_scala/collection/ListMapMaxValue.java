package com.ailois.common_scala.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class ListMapMaxValue {

    private static final Logger logger = LoggerFactory.getLogger(ListMapMaxValue.class);

    public static void main(String[] args) {

        List<HashMap<String, Integer>> data = generateData();
        logger.info("maxSortStream result: {}", maxSortStream((data)));
        logger.info("maxStream result: {}", maxStream((data)));

    }

    private static List<HashMap<String, Integer>> generateData() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        HashMap<String, Integer> map1 = new HashMap<>();
        map1.put("a", 2);
        HashMap<String, Integer> map2 = new HashMap<>();
        map2.put("a", 3);
        HashMap<String, Integer> map3 = new HashMap<>();
        map3.put("a", 4);
        HashMap<String, Integer> map4 = new HashMap<>();
        map4.put("a", 5);
        HashMap<String, Integer> map5 = new HashMap<>();
        map5.put("a", 6);
        List<HashMap<String, Integer>> data = new ArrayList<>();
        data.add(map);
        data.add(map1);
        data.add(map2);
        data.add(map3);
        data.add(map4);
        data.add(map5);
        return data;
    }

    private static Integer maxStream(List<HashMap<String, Integer>> data) {
        Integer integer = null;
        Optional<HashMap<String, Integer>> maxOptional = data.stream().max(Comparator.comparing(x -> x.get("a")));
        if (maxOptional.isPresent()) {
            integer = maxOptional.get().get("a");
        }
        return integer;
    }

    private static Integer maxSortStream(List<HashMap<String, Integer>> data) {
        List<HashMap<String, Integer>> collect = data.stream().sorted((x1, x2) -> x2.get("a").compareTo(x1.get("a"))).collect(Collectors.toList());
        return collect.get(0).get("a");
    }

}
