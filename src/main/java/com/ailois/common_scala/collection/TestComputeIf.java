package com.ailois.common_scala.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TestComputeIf {

    private static final Logger logger = LoggerFactory.getLogger(TestComputeIf.class);

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        String key = "1";
        // if present 是如果存在就修改
        map.computeIfPresent(key, (k, v) -> "");
        // if absent 是如果不存在就添加
        map.computeIfAbsent(key, x -> map.put(x, "3"));
        logger.info("{}", map);

    }

}
