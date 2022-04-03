package com.ailois.common_scala.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TestListRemove {

    private static final Logger logger = LoggerFactory.getLogger(TestListRemove.class);

    public static void main(String[] args) {

        List<TestClass> result = new ArrayList<>();
        TestClass testClass = new TestClass();
        testClass.setAge(12);
        testClass.setName("AA");

        result.add(testClass);
        if (result.size() >= 2) {
            result.get(result.size() - 2)
                    .setAge(result.get(result.size() - 1).getAge()
                            + result.get(result.size() - 2).getAge());
            result.remove(result.size() - 1);
        }

        logger.info("result: {}", result);

    }

    private static class TestClass {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "TestClass{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


}
