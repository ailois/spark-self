package com.ailois.common_scala.collection

object TestScalaSlowly {

  def main(args: Array[String]): Unit = {
    testWhileAndFor()

    println()
    println()
    println()

    testMap()
  }

  def testWhileAndFor(): Unit = {
    val aaa = 0 until 1000000
    val start = System.currentTimeMillis
    var total = 0;
    for (i <- aaa) {
      total += i
    }
    val end = System.currentTimeMillis
    println(end - start)

    val start1 = System.currentTimeMillis
    var total1 = 0
    var i = 0
    while ( {
      i < 1000000
    }) {
      i = i + 1
      total1 += i
    }
    val end1 = System.currentTimeMillis
    println(end1 - start1)

  }

  def testMap(): Unit = {

    val m = new scala.collection.mutable.HashMap[Int, Int];
    var i = 0
    val start = System.currentTimeMillis
    while (i < 1000000) {
      i = i + 1
      m.put(i, i)
    }
    val end = System.currentTimeMillis
    println(end - start)
    //    println(m.size)

    val m1 = new java.util.HashMap[Int, Int]
    var i1 = 0
    val start1 = System.currentTimeMillis
    while (i1 < 1000000) {
      i1 = i1 + 1
      m1.put(i1, i1)
    }
    val end1 = System.currentTimeMillis
    println(end1 - start1)
    //    println(m1.size)

    val m2 = new java.util.concurrent.ConcurrentHashMap[Int, Int]
    var i2 = 0
    val start2 = System.currentTimeMillis
    while (i2 < 1000000) {
      i2 = i2 + 1
      m2.put(i2, i2)
    }
    val end2 = System.currentTimeMillis
    println(end2 - start2)
    //    println(m2.size)

  }

}
