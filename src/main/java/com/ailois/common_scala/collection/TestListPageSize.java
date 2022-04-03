package com.ailois.common_scala.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TestListPageSize {

    private static final Logger logger = LoggerFactory.getLogger(TestListPageSize.class);

    public static void main(String[] args) {
        List<Integer> list = formList();
        logger.info("result: {}", list.subList(34, 34));
    }

    public static List<Integer> formList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            list.add(i);
        }
        return list;
    }


}
