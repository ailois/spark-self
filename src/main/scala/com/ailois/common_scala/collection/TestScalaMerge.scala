package com.ailois.common_scala.collection

import scala.collection.mutable
import scala.language.postfixOps

object TestScalaMerge {

  def main(args: Array[String]): Unit = {

    // create a HashMap and add some values
    val map1 = mutable.Map[Int, String]()

    map1.put(1, "L")
    map1.put(2, "M")
    map1.put(3, "N")
    map1.put(4, "H")

    val map2 = mutable.Map[Int, String]()
    map2.put(1, "B")
    map2.put(2, "G")
    map2.put(3, "R")
    map2.put(5, "E")

    // print map details
    println("HashMap1: " + map1.toString)

    println("HashMap2: " + map2.toString)

    println(merge(map1, map2))


  }

  def merge[K, V](m1:mutable.Map[K, V], m2:mutable.Map[K, V]):Map[K, String] =
    (m1.keySet ++ m2.keySet) map { i => i -> (m1.get(i).toList ::: m2.get(i).toList).mkString("_") } toMap

}
