package com.ailois.jobs

import com.ailois.executor.SparkInit
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.{Dataset, Row, SQLContext, SparkSession}

object TestRepartition {

  val spark: SparkSession = SparkInit.sparkInstance()
  val sparkContext: SQLContext = spark.sqlContext

  import sparkContext.implicits._

  def main(args: Array[String]): Unit = {
    val dataOne = Seq("1", "2", "2", "3", "3", "3").toList.toDF("key")
    val dataTwo = Seq(("1", null), ("1", null), ("1", null), ("1", null), ("1", null), ("1", null))
      .toList.toDF("key", "value")

    dataOne.show(false)
    dataTwo.show(false)

    foreachData(dataOne, "key", "one")
    foreachData(dataTwo.repartition(6, col("value")), "key", "two")

  }

  def foreachData(data: Dataset[Row], key: String, printSign: String): Unit = {
    data.rdd
      .foreachPartition(x => {
        var p = printSign
        while(x.hasNext){
          p = p.concat(x.next.getAs[String](key))
        }
        println(p)
      })
  }

}
