package com.ailois.common_scala.collection

object WordCount {

  def main(args: Array[String]): Unit = {
    val data = generateData
    println(wordCount1(data))
    println(wordCount2(data))
  }

  def generateData: List[String] = {
    List("hello tom hello jerry", "hello jerry", "hello kitty")
  }

  def wordCount1(data: List[String]): Map[String, Int] = {
    data.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).mapValues(_.foldLeft(0)(_ + _._2))
  }

  def wordCount2(data: List[String]): List[(String, Int)] = {
    data.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).map(t => (t._1, t._2.size)).toList.sortBy(_._2).reverse
  }

}
