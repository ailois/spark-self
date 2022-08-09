package com.ailois.mlib

import com.ailois.executor.SparkInit
import org.apache.spark.ml.linalg.{Matrices, Matrix}
import org.apache.spark.sql.{SQLContext, SparkSession}

object BasicValue {

  val spark: SparkSession = SparkInit.sparkInstance()
  val sparkContext: SQLContext = spark.sqlContext

  def main(args: Array[String]): Unit = {
    val dm: Matrix = Matrices.dense(3, 2, Array(1.0, 3.0, 5.0, 2.0, 4.0, 6.0))
    println(dm)
    println("====================================================================================")
    val sm: Matrix = Matrices.sparse(3, 2, Array(0, 1, 3), Array(0, 2, 1), Array(9, 6, 8))
    println(sm)
    println("====================================================================================")
    println(sm.toDense)
  }

}
