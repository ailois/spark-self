package com.ailois.es;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestESQueryStrBuild {

    private static final Logger logger = LoggerFactory.getLogger(TestESQueryStrBuild.class);

    public static void main(String[] args) {

        Integer deviceUserMaxCountStart = 1;
        Integer deviceUserMaxCountEnd = 2;

        String rangeTemplate = "{\"range\":{\"%s\": {\"gte\": \"%s\",\"lte\": \"%s\"}}}";
        String deviceUserMaxCount = String.format(rangeTemplate, "device_user_max_count", deviceUserMaxCountStart, deviceUserMaxCountEnd);
        logger.info("deviceUserMaxCount: {}", deviceUserMaxCount);

        String a = "{\"query\":{\"bool\":{\"must\":[{\"range\":{\"device_user_max_count\":{\"gte\":\"\",\"lte\":\"\"}}},{\"range\":{\"ip_user_count\":{\"gte\":\"10\",\"lte\":\"20\"}}},{\"range\":{\"province_count_7days_domestic\": {\"gte\": \"10\",\"lte\": \"20\"}}},{\"range\": {\"province_count_1hour_domestic\": {\"gte\": 10,\"lte\": 20}}},{\"range\": {\"first_review_interval\": {\"gte\": 10,\"lte\": 20}}},{\"wildcard\": {\"ip_device_2province_label\": {\"value\": \"*\"}}},{\"wildcard\": {\"ip_phone_2province_label\": {\"value\": \"*\"}}}]}}}";
        String b = a.replace(" ", "");
        logger.info("b: {}", b);

        String test = "%s:dasdasdas";
        String result = String.format(test, "");
        logger.info("result: {}", result);

    }

}
