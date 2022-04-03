package com.ailois.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;

public class TestJsonObject {

    @SuppressWarnings("all")
    public static void main(String[] args) {
        String json = "{\n" +
                "    \"query\":{\n" +
                "        \"match\":{\n" +
                "            \"obj_id\":\"2301\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"out_agg\":{\n" +
                "        \"name\":\"groupByIp\",\n" +
                "        \"method\":\"terms\",\n" +
                "        \"field\":\"review_ip_dot.keyword\"\n" +
                "    },\n" +
                "    \"in_agg\":{\n" +
                "        \n" +
                "    }\n" +
                "}";
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        String query = jsonObject.get("query").toString();


        HashMap<String, String> outAgg = new Gson().fromJson(jsonObject.get("in_agg").getAsJsonObject().toString(),
                new HashMap<String, String>().getClass());

        System.out.println(outAgg.isEmpty());
        System.out.println(outAgg);
        System.out.println(outAgg.get("name"));

        System.out.println(query);

    }

}
