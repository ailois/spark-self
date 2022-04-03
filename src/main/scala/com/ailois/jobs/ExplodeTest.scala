package com.ailois.jobs

import com.ailois.executor.SparkInit
import org.apache.spark.sql.Row
import org.apache.spark.sql.functions.{col, explode}

object ExplodeTest {

  def main(args: Array[String]): Unit = {

    val spark = SparkInit.sparkInstance()
    val sparkContext = spark.sqlContext
    import sparkContext.implicits._

    // join explode version
    val mapDF = Map("a" -> List("c", "d", "e"), "b" -> List("f", "g", "h")).toList.toDF("key", "value")
    val df = List(("a", 1.0), ("b", 2.0)).toDF("x", "y")
    val joinedDF = df.join(mapDF, df("x") === mapDF("key"), "inner")
      .select("value", "y")
      .withColumn("value", explode(col("value")))

    mapDF.show()
    df.show()
    joinedDF.show()

    // flatmap version
    val map = Map("a" -> List("c","d","e"), "b" -> List("f","g","h"))
    val dfNew = List(("a", 1.0), ("b", 2.0)).toDF("x", "y")

    val rdd = dfNew.rdd.flatMap{ row =>
      val x = row.getAs[String]("x")
      val y = row.getAs[Double]("y")
      for(v <- map(x)) yield Row(v,y)
    }
    val df2 = spark.createDataFrame(rdd, dfNew.schema)
    df2.show()
    df2.write.format("").partitionBy("","")

  }

}
