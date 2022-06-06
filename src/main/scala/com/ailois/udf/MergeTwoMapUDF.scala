package com.ailois.udf

import com.ailois.executor.SparkInit
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.{callUDF, col}
import org.apache.spark.sql.{SQLContext, SparkSession}

import scala.collection.mutable

/**
 * Merge two fields of map type, and add the value values of the same key
 */
object MergeTwoMapUDF {

  val spark: SparkSession = SparkInit.sparkInstance()
  val sparkContext: SQLContext = spark.sqlContext

  import sparkContext.implicits._

  def main(args: Array[String]): Unit = {
    val originDF = mutable.Seq(("id_one", Map(1 -> 1), Map(2 -> 1)), ("id_two", Map(1 -> 2), Map(1 -> 1)))
      .toDF("key", "value_1", "value_2")

    originDF.withColumn("merge", mergeMap(col("value_1"), col("value_2")))
      .show(false)
  }

  def mergeMap: UserDefinedFunction = spark.udf.register("merge_map", (a1: Map[Int, Int], a2: Map[Int, Int]) => {
    // method1: getOrDefault
    // a1 ++ a2.map(t => t._1 -> (a1.getOrElse(t._1, 0) + t._2))

    // method2: foldLeft
    // a1.foldLeft(a2)((map, kv) => {
    //   map + (kv._1 -> (kv._2 + map.getOrElse(kv._1, 0)))
    // })

    // method3: pattern matching
    a1 ++ a2.map{ case(k, v) => k -> (v + a1.getOrElse(k, 0))}
  })

}
