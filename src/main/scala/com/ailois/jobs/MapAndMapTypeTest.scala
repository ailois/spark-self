package com.ailois.jobs

import com.ailois.executor.SparkInit
import org.apache.spark.sql.catalyst.encoders.{ExpressionEncoder, RowEncoder}
import org.apache.spark.sql.types.DataTypes.{StringType, createMapType}
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{DataFrame, Row, SQLContext, SparkSession}

import scala.collection.mutable

object MapAndMapTypeTest {

  val spark: SparkSession = SparkInit.sparkInstance()
  val sparkContext: SQLContext = spark.sqlContext

  import sparkContext.implicits._

  def main(args: Array[String]): Unit = {

    val originDF = mutable.Seq(("id_1", "a", "", "1"), ("id_2", "b", "", "2"), ("id_3", "c", "", "3")).
      toDF("key", "value", "Def_first", "Def_second")
    val schema = originDF.schema
    dataFrameMap(originDF, schema).show(false)
    rddMap(originDF, schema).show(false)

  }

  def dataFrameMap(originDF: DataFrame, schema: StructType): DataFrame = {
    val defMapType = createMapType(StringType, StringType, true)
    val flatSchema: StructType = schema.add("psv", defMapType)
    val columns = originDF.columns
    implicit val encoder: ExpressionEncoder[Row] = RowEncoder.apply(flatSchema)
    originDF.map(row => {
      val fields = row.mkString(",").split(",")
      val key1 = columns(2).split("_", 2)(1)
      val value1 = row(2).toString
      val key2 = columns(3).split("_", 2)(1)
      val value2 = row(3).toString
      val defMap = Map(key1 -> value1, key2 -> value2)
      Row.fromSeq(Seq(fields(0), fields(1), fields(2), fields(3), defMap))
    })
  }

  def rddMap(originDF: DataFrame, schema: StructType): DataFrame = {
    val explodeDF = originDF.rdd.map {
      row => {
        val fields = row.mkString(",").split(",")
        Row.fromSeq(fields)
      }
    }
    spark.createDataFrame(explodeDF, schema)
  }

}
