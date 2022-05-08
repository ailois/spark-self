package com.ailois.jobs

import com.ailois.executor.SparkInit
import org.apache.spark.sql.catalyst.encoders.{ExpressionEncoder, RowEncoder}
import org.apache.spark.sql.{Encoders, Row, SQLContext, SparkSession}

import scala.collection.mutable

object ScalaGroupByKey {

  val spark: SparkSession = SparkInit.sparkInstance()
  val sparkContext: SQLContext = spark.sqlContext

  import sparkContext.implicits._

  def main(args: Array[String]): Unit = {

    val originDF = mutable.Seq(("id_1", Map(1 -> 1)), ("id_1", Map(1 -> 2)), ("id_1", Map(2 -> 2)),
                               ("id_2", Map(1 -> 2)), ("id_2", Map(1 -> 3)), ("id_2", Map(2 -> 1)))
                          .toDF("key", "value")

    val schema = originDF.schema
    implicit val encoder: ExpressionEncoder[Row] = RowEncoder.apply(schema)
    val result = originDF.groupByKey(_.getString(0))(Encoders.STRING).mapGroups((key, values) => {
      val maps = mutable.ArrayBuffer[Map[Int, Int]]()
      while (values.hasNext) {
        //        maps += values.next().getAs("value").asInstanceOf[Map[String, Int]]
        maps += values.next().getAs[Map[Int, Int]]("value")
      }
      val res = maps.flatMap(_.toMap).foldLeft(mutable.Map[Int, Int]())((map, y) => {
        map(y._1) = map.getOrElse(y._1, 0) + y._2
        map
      }).toList.sortBy(_._1).toMap
      Row.fromSeq(Seq(key, res))
    })
    result.orderBy("key").show(false)
  }

}
