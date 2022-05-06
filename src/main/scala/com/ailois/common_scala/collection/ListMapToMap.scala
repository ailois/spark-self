package com.ailois.common_scala.collection

import scala.collection.mutable

object ListMapToMap {

  def main(args: Array[String]): Unit = {
    val data = List(Map("a" -> 1), Map("a" -> 2), Map("b" -> 1))
    println(data.flatMap(_.toMap).foldLeft(mutable.Map[String, Int]())((x, y) => {
      x(y._1) = x.getOrElse(y._1, 0) + y._2
      x
    }))

  }

}
