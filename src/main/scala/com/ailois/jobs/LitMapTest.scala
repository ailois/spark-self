package com.ailois.jobs

import com.ailois.executor.SparkInit
import org.apache.spark.sql.functions.{col, typedLit}
import org.apache.spark.sql.{SQLContext, SparkSession}

object LitMapTest {

  val spark: SparkSession = SparkInit.sparkInstance()
  val sparkContext: SQLContext = spark.sqlContext

  import sparkContext.implicits._

  def main(args: Array[String]): Unit = {

    var mapDF = Map("a?" -> 1, "b?" -> 2, "c?" -> 3).toList.toDF("key", "value")
    mapDF = mapDF.withColumn("map", typedLit[Map[String, Int]](Map("1" -> 1)))
    mapDF.filter(col("key").isNull)
    mapDF.show(false)

  }

}
