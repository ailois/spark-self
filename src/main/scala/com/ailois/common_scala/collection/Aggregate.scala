package com.ailois.common_scala.collection

import scala.collection.JavaConverters.asJavaIterableConverter

object Aggregate {

  def main(args: Array[String]): Unit = {
    val data = generateData
    println(getSumInt1(data))
    println(getSumInt2(data))
  }

  def generateData: List[List[Int]] = {
    List(List(1, 2, 3), List(3, 4, 5), List(2), List(0))
  }

  def getSumInt1(data: List[List[Int]]): Int = {
    data.aggregate(0)(_ + _.sum, _ + _)
  }

  def getSumInt2(data: List[List[Int]]): Int = {
    data.aggregate(0)((x1, x2) => x1 + x2.sum, (x1, x2) => x1 + x2)
  }

}
