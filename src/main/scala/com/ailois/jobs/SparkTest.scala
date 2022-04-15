package com.ailois.jobs

import com.ailois.executor.SparkInit
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.{SQLContext, SparkSession}

object SparkTest {

  val spark: SparkSession = SparkInit.sparkInstance()
  val sparkContext: SQLContext = spark.sqlContext

  import sparkContext.implicits._

  def main(args: Array[String]): Unit = {

    val mapDF = Map("a" -> 1, "b" -> 2, "c" -> 3).toList.toDF("key", "value")
    val data = mapDF.select(col("value").cast("int"))
    data.printSchema()
    data.show(false)

  }

}
