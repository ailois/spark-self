package com.ailois.jobs

import com.ailois.executor.SparkInit
import org.apache.spark.sql.{SQLContext, SparkSession}

object SparkTest {

  val spark: SparkSession = SparkInit.sparkInstance()
  val sparkContext: SQLContext = spark.sqlContext

//  import sparkContext.implicits._

  def main(args: Array[String]): Unit = {

    spark.sql("select * from belard.video_games_sales limit 10").show(false)
    spark.stop()

  }

}
