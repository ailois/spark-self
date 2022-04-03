package com.ailois.str;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("all")
public class TestStrToMap {

    public static void main(String[] args) {

//        String origin = "(2020-02-28,Netease,4b952ae0-4a1a-4696-93a0-515d250c2367)";
        String origin = "(2020-03-17,OPPO_OPPO A59s,)";
//        String origin = "(2020-02-28,Netease,4b952ae0-4a1a-4696-93a0-515d250c2367),(2020-02-28,HUAWEI,07fd26a3-c30d-44f7-b24f-e082fe12755a)";
        String[] temp = origin.split("\\),\\(");
        List<HashMap<String, String>> lists = new ArrayList<>();

        for (String a : temp) {
            System.out.println(a);
        }


        if (temp.length == 1) {
            temp[0] = temp[0].substring(1, temp[0].length() - 1);
        }
        for (int i = 0; i < temp.length; i++) {
            HashMap<String, String> map = new HashMap<>();
            if (temp.length != 1 && i == 0) {
                temp[i] = temp[i].substring(1);
            } else if (temp.length != 1 && i == (temp.length - 1)) {
                temp[i] = temp[i].substring(0, temp[i].length() - 1);
            }
            String[] device = temp[i].split(",");
            map.put("设备名称", device[1]);
            if (device.length == 2) {
                map.put("device_id", null);
            } else {
                map.put("device_id", device[2]);
            }
            map.put("登陆日期", device[0]);
            lists.add(map);
        }

        for (HashMap<String, String> a : lists) {
            System.out.println(a);
        }

    }

}
