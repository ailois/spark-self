package com.ailois.jobs

import scala.collection.mutable

object ScalaTest {

  def main(args: Array[String]): Unit = {
    val reValues = Array.range(0, 8).map(x => "?").toList.mkString(",")
    println(reValues)
    for (i <- Array.range(0, 2)) {
      println(i)
    }
    var ls = mutable.ArrayBuffer[String]()
    ls = ls.+:("1")
    println(ls.size)
    ls = mutable.ArrayBuffer[String]()

  }

}
