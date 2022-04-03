package com.ailois.common_scala.collection;

import com.ailois.annotation.MethodTime;
import com.google.common.collect.Sets;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestFilterBetweenStreamAndGoogle {

    private static final Logger logger = LoggerFactory.getLogger(TestFilterBetweenStreamAndGoogle.class);

    public static void main(String[] args) {

        final Set<Person> set1 = new HashSet<>();
        for (int i = 0; i < 10000000; i++) {
            set1.add(new Person("a", i));
        }

        final Set<Person> set2 = new HashSet<>();
        for (int i = 0; i < 10000000; i+=2) {
            set2.add(new Person("a", i));
        }

        testGuava(set1, set2);
        testStream(set1, set2);

    }

    private static void testGuava(Set<Person> set1, Set<Person> set2) {
        Predicate<Person> predicate = set2::contains;
        Set<Person> filter = Sets.filter(set1, predicate::test);
        logger.info("guava filter size: {}", filter.size());
    }

    private static void testStream(Set<Person> set1, Set<Person> set2) {
        Set<Person> people = set1.stream().filter(item -> !set2.contains(item)).collect(Collectors.toSet());
        logger.info("stream filter size: {}", people.size());
    }

}

@Data
class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
