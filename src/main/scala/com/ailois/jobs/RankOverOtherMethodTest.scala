package com.ailois.jobs

import com.ailois.executor.SparkInit
import org.apache.spark.sql.catalyst.encoders.{ExpressionEncoder, RowEncoder}
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, rank}
import org.apache.spark.sql.{DataFrame, Row, SQLContext, SparkSession}

import java.util
import scala.collection.mutable

object RankOverOtherMethodTest {

  val spark: SparkSession = SparkInit.sparkInstance()
  val sparkContext: SQLContext = spark.sqlContext

  import sparkContext.implicits._

  def main(args: Array[String]): Unit = {
    var originDF = mutable.Seq(("1", "01", "lvl_a", "1"), ("1", "01", "lvl_a", "2"),
      ("2", "02", "lvl_b", "1"), ("2", "02", "lvl_b", "2"), ("3", "03", "lvl_c", "1"), ("3", "03", "lvl_c", "2"))
      .toDF("uid", "position_id", "level_12", "version")
    originDF = originDF.repartition(5, col("uid"), col("position_id"))
    val result01 = getLatestWithRankOver(originDF)
    result01.show(false)
//    val result02 = sortPartitionAndDistinct(originDF)
//    result02.show(false)
//    val result03 = sortPartitionAndMapPartition(originDF)
//    result03.show(false)
  }

  // 测试下来最快
  def sortPartitionAndDistinct(originDF: DataFrame): DataFrame = {
    val l1 = System.currentTimeMillis()
    val res = originDF.sortWithinPartitions("version").dropDuplicates("uid", "position_id")
    val l2 = System.currentTimeMillis()
    println(l2 - l1)
    res
  }

  def getLatestWithRankOver(originDF: DataFrame): DataFrame = {
    val l1 = System.currentTimeMillis()
    val data = originDF.withColumn("rn", rank().over(Window.partitionBy(col("uid"),
      col("position_id")).orderBy(col("version")))).where("rn = 1").drop("rn")
    val l2 = System.currentTimeMillis()
    println(l2 - l1)
    data
  }

  def sortPartitionAndMapPartition(originDF: DataFrame): DataFrame = {
    val l1 = System.currentTimeMillis()
    val schema = originDF.schema
    val sort = originDF.sortWithinPartitions("version")
    implicit val encoder: ExpressionEncoder[Row] = RowEncoder.apply(schema)
    val result = sort.mapPartitions((iterator: util.Iterator[Row]) => {
      val stack = new util.Stack[Row]
      while (iterator.hasNext) {
        val row = iterator.next()
        if (stack.isEmpty || stack.peek().get(0) != row.get(0)) stack.push(row)
      }
      stack.iterator
    }, encoder)
    val l2 = System.currentTimeMillis()
    println(l2 - l1)
    result
  }

}
