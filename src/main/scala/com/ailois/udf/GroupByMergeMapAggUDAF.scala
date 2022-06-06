package com.ailois.udf

import com.ailois.executor.SparkInit
import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql.functions.{col, udaf}
import org.apache.spark.sql.{Encoder, SQLContext, SparkSession}

import scala.collection.mutable

object GroupByMergeMapAggUDAF {

  val spark: SparkSession = SparkInit.sparkInstance()
  val sparkContext: SQLContext = spark.sqlContext

  import sparkContext.implicits._

  def main(args: Array[String]): Unit = {

    val origin = mutable.Seq((1, Map("John" -> 12.5, "Alice" -> 5.5)),
      (1, Map("Jim" -> 16.5, "Alice" -> 4.0)),
      (2, Map("John" -> 13.5, "Jim" -> 2.5)))
      .toDF("id", "scoreMap")

    origin.groupBy("id")
      .agg(udaf(mergeMapAgg(_ + _)).apply(col("scoreMap")).name("scoreMap"))
      .show(false)

  }

  /**
   * nested classes need to use WeakTypeTag
   * so it can not use Map[K, V] to replace a specific type, such as Map[String, Double]
   **/
  def mergeMapAgg(mergeMap: (Double, Double) => Double): Aggregator[Map[String, Double], Map[String, Double], Map[String, Double]] =
    new Aggregator[Map[String, Double], Map[String, Double], Map[String, Double]]{

    override def zero: Map[String, Double] = Map[String, Double]()

    override def reduce(b: Map[String, Double], a: Map[String, Double]): Map[String, Double] = {
      b ++ a.map { case (k,v) => k -> b.get(k).map(mergeMap(v, _)).getOrElse(v) }
    }

    override def merge(b1: Map[String, Double], b2: Map[String, Double]): Map[String, Double] = reduce(b1, b2)

    override def finish(reduction: Map[String, Double]): Map[String, Double] = reduction

    override def bufferEncoder: Encoder[Map[String, Double]] = implicitly[Encoder[Map[String, Double]]]

    override def outputEncoder: Encoder[Map[String, Double]] = implicitly[Encoder[Map[String, Double]]]
  }

}




