package com.ailois.common_scala.collection

object FoldLeft {

  def main(args: Array[String]): Unit = {
    val data = generateData
    val data2 = generateData2
    println(foldLeft(data))
    println(foldLeft2(data))
    println(reduceLeft(data2))
    println(scanLeft(data))
  }

  def generateData: List[Int] = {
    List(1, 2, 3, 4)
  }

  def generateData2: List[Int] = {
    List(5, 1, 2, 3, 4)
  }

  def minus(num1: Int, num2: Int): Int = {
    num1 - num2
  }

  def foldLeft(data: List[Int]): Int = {
    data.foldLeft(5)(minus)
  }

  def foldLeft2(data: List[Int]): Int = {
    (5 /: data) (minus)
  }

  def reduceLeft(data: List[Int]): Int = {
    data.reduceLeft(minus)
  }

  def scanLeft(data: List[Int]): List[Int] = {
    data.scanLeft(5)(minus)
  }

}
