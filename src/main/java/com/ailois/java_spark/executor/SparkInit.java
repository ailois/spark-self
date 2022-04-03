package com.ailois.java_spark.executor;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;

public class SparkInit {

    public SparkSession javaSparkInit() {
        SparkConf sparkConf = new SparkConf().setAppName("TestJavaSpark").setMaster("local[3]");
        return new SparkSession.Builder().config(sparkConf).enableHiveSupport().getOrCreate();
    }

}
