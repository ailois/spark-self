package com.ailois.common_scala.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TestLongSort {

    private static final Logger logger = LoggerFactory.getLogger(TestLongSort.class);

    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(3L);
        list.add(2L);
        list.sort(Long::compareTo);
        list.remove(5L);

        List<Long> collect = list.stream().filter(x -> x != 1L).collect(Collectors.toList());
        logger.info("collect: {}", collect);

        String randomStr = randomStr();
        logger.info("randomStr: {}", randomStr);

    }

    private static String randomStr() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random(10);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            int number = random.nextInt(str.length());
            char charAt = str.charAt(number);
            sb.append(charAt);
        }
        return sb.toString();
    }

}
