package com.ailois.jobs

import com.ailois.executor.SparkInit
import org.apache.spark.sql.{SQLContext, SparkSession}

object NoHeaderTest {

  val spark: SparkSession = SparkInit.sparkInstance()
  val sparkContext: SQLContext = spark.sqlContext

  def main(args: Array[String]): Unit = {

    // load path must contains file if hdfs-site.xml in the same folder
    val data = spark.read.format("csv")
      .option("header", value = true)
      .option("inferSchema", value = true)
      .load("file:/D:\\IdeaProjects\\spark-self\\src\\main\\resources\\test_no_header.txt")
    spark.sessionState.executePlan(data.queryExecution.logical).optimizedPlan.stats.sizeInBytes
  }
}
