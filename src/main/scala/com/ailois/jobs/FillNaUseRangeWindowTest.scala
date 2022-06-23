package com.ailois.jobs

import com.ailois.executor.SparkInit
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{avg, coalesce, col}
import org.apache.spark.sql.{SQLContext, SparkSession}

import scala.collection.mutable

object FillNaUseRangeWindowTest {

  val spark: SparkSession = SparkInit.sparkInstance()
  val sparkContext: SQLContext = spark.sqlContext
  val filePath = "file:/D:\\IdeaProjects\\spark-self\\src\\main\\resources\\fill_na"

  import sparkContext.implicits._

  def main(args: Array[String]): Unit = {
//    dataInit()
    val data = spark.read.parquet(filePath)
    println("total data size: " + data.count())
    val window = Window.orderBy("time").rowsBetween(-4, 4)
    data.withColumn("value_new", coalesce(col("value"), avg("value").over(window)))
      .where("time >= 1655957896710 and time <= 1655957896720")
      .show(200, truncate = false)
  }

  def dataInit(): Unit = {
    var ls = mutable.Map[Long, String]()
    val time = System.currentTimeMillis()
    for (a <- 1 to 2000000) {
      ls.+=(time + a -> randomNumber())
    }
    ls.toList.toDF("time", "value")
      .withColumn("value", col("value").cast("double"))
      .write.parquet(filePath)
  }

  def randomNumber(): String = {
    val int = scala.util.Random.nextInt(100)
    if (int % 10 == 0) return null
    int.toString
  }

}
