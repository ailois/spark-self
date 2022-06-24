package com.ailois.jobs

import com.ailois.executor.SparkInit
import org.apache.spark.sql.{SQLContext, SparkSession}

object DataSetAliasJoinTest {

  val spark: SparkSession = SparkInit.sparkInstance()
  val sparkContext: SQLContext = spark.sqlContext

  import sparkContext.implicits._

  def main(args: Array[String]): Unit = {
    val dataLeft = List(("a", 1.0), ("b", 2.0)).toDF("x", "y")
    val dataRight = List(("a", 2.0), ("b", 3.0), ("c", 4.0)).toDF("x", "y")
    dataLeft.as("l").join(dataRight.as("r"), Seq("x"), "left")
      .selectExpr("l.*", "r.y as z").show(false)
  }

}
