package com.ailois.executor

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

class SparkInit private {

  def init(): SparkSession = {
    val conf = new SparkConf()
      .setAppName("Test Spark")
      //      .setMaster("yarn")
      //            .setMaster("spark://127.0.0.1:7077")
      .setMaster("local[*]")
      .set("hive.metastore.uris", "thrift://master:9083")
      .set("spark.hadoop.metastore.catalog.default", "hive")
      .set("spark.sql.hive.convertMetastoreOrc", "false")
      .set("yarn.resourcemanager.hostname", "127.0.0.1")
      .set("spark.executor.instances", "2")
      .set("spark.executor.cores", "2")
      .set("spark.executor.memory", "2048M")
      .set("spark.driver.host", "127.0.0.1")

    //      .setJars(List("D:\\develop_soft\\idea_workspace_2018\\sparkdemo\\target\\sparkdemo-1.0-SNAPSHOT.jar"
    //    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")

    val spark = new SparkSession.Builder().config(conf)
      .enableHiveSupport()
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    spark
  }

}

object SparkInit {
  val sparkInit = new SparkInit

  def sparkInstance(): SparkSession = {
    sparkInit.init()
  }

}
