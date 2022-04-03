package com.ailois.common_scala.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestFilter {

    private static final Logger logger = LoggerFactory.getLogger(TestFilter.class);

    public static void main(String[] args) {

        Integer[] temp1 = {2, 3};
        Integer[] temp2 = {1, 2, 3, 4};

        List<Integer> a = Arrays.asList(temp1);
        List<Integer> b = Arrays.asList(temp2);

        logger.info("{}", b.stream().filter(o -> !a.contains(o)).collect(Collectors.toList()));

    }

}
