package com.ailois.jobs

import com.ailois.executor.SparkInit
import org.apache.spark.sql.functions.{col, count, countDistinct}
import org.apache.spark.sql.{SQLContext, SparkSession}

object ColumnToListTest {

  val spark: SparkSession = SparkInit.sparkInstance()
  val sparkContext: SQLContext = spark.sqlContext

  import sparkContext.implicits._

  def main(args: Array[String]): Unit = {
    val data = List((1, "1.0"), (1, "2.0"), (3, "3.0")).toDF("id", "value")
    data.groupBy("id").agg(countDistinct("id")).show(false)
    val ids = List(1, 2).toDF("id")
    data.filter(col("id").isin(ids.select("id").collect().map(_.getInt(0)).toList : _*)).show(false)
  }

}
