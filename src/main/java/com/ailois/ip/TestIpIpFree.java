package com.ailois.ip;

import net.ipip.ipdb.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class TestIpIpFree {

    private static final Logger logger = LoggerFactory.getLogger(TestIpIpFree.class);
    private static final String PATH = "/src/main/resources/ipipfree.ipdb"; //NOSONAR


    public static void main(String[] args) {
        try {
            City db = new City(PATH);
            String join = String.join("-", Arrays.asList(db.find(longToIp(1703094087L), "CN")));
            logger.info("{}", join);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String longToIp(long ip) {
        return (ip >> 24) +
                "." +
                ((ip & 0x00FFFFFF) >> 16) +
                "." +
                ((ip & 0x0000FFFF) >> 8) +
                "." +
                (ip & 0x000000FF);
    }

    @SuppressWarnings("all")
    private static String ipResolve(String ip) {
        String result = null;
        try {
            City db = new City(PATH);
            result = String.join("-", Arrays.asList(db.find(longToIp(Long.parseLong(ip)), "CN")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
