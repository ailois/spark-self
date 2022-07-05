package com.ailois.jobs

import com.ailois.executor.SparkInit
import org.apache.spark.sql.functions.{col, regexp_replace}
import org.apache.spark.sql.{SQLContext, SparkSession}

object SparkTest {

  val spark: SparkSession = SparkInit.sparkInstance()
  val sparkContext: SQLContext = spark.sqlContext

  import sparkContext.implicits._

  def main(args: Array[String]): Unit = {

    val mapDF = Map("a?" -> 1, "b?" -> 2, "c?" -> 3).toList.toDF("key", "value")
    val data = mapDF.withColumn("key", regexp_replace(col("key"), "\\?", "\\$"))
      .select(col("key"), col("value").cast("int"))
    data.filter(x => x.get(1).toString.contains("3")).show(false)
    data.printSchema()
    data.show(false)

    /*
    * Try to directly call a fixed number of executors to do some operations
    **/
    data.repartition(100).rdd.foreachPartition(_ => println("==**=="))

  }

}
