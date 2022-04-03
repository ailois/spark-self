package com.ailois.str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("all")
public class TestStrToList {

    public static void main(String[] args) {

        String esStr = "(185496,2),(157638,1),(161910,1),(137515,1),(180786,1),(130651,2),(58881,3),(168332,4),(70253,1),(57161,2),(186399,3),(12623,1),(2301,2),(130630,2),(55307,2),(10056,1),(133336,1),(185634,1),(37681,2)";
        List<List<String>> list = convertEsStringToList(esStr);
        for (List<String> strs : list) {
            for (String str : strs) {
                System.out.println(str);
            }
        }
    }

    public static List<List<String>> convertEsStringToList(String esStr) {
        if (null == esStr) {
            return new ArrayList<>();
        }
        String[] temp = esStr.split("\\),\\(");
        if (temp.length == 1) {
            temp[0] = temp[0].substring(1, temp[0].length() - 1);
        }
        List<List<String>> lists = new ArrayList<>();
        for (int i = 0; i < temp.length; i++) {
            if (temp.length != 1 && i == 0) {
                temp[i] = temp[i].substring(1);
            } else if (temp.length != 1 && i == (temp.length - 1)) {
                temp[i] = temp[i].substring(0, temp[i].length() - 1);
            }
            lists.add(Arrays.asList(temp[i].split(",")));
        }
        return lists;
    }

}
