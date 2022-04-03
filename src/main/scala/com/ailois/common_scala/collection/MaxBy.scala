package com.ailois.common_scala.collection

object MaxBy {

  def main(args: Array[String]): Unit = {
    printf(getMaxString(generateData))
  }

  def generateData: List[(String, Int)] = {
    List("a" -> 5, "b" -> 4, "c" -> 3, "d" -> 2, "e" -> 1)
  }

  def getMaxString(data: List[(String, Int)]): String = {
    data.maxBy(_._2)._1
  }

}
