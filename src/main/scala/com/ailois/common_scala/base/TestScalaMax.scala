package com.ailois.common_scala.base

import java.util.Date

import org.apache.commons.lang3.time.DateFormatUtils

object TestScalaMax {

  def main(args: Array[String]): Unit = {

    val bloom = new Bloom(1 << 29)
    val str = "d0e226bf-e041-419c-92cb-0dad81648d51"
    println(bloom.hash(str, 61))
    println(getTodayDate)
    val cap = if (1 << 29 > 0) 1 << 29 else 1 << 27
    println(1 << 29)
    println(cap)

  }

  class Bloom(size: Long) extends Serializable {
    private val cap = if (size > 0) size else 1 << 27

    def hash(value: String, seed: Int): Long = {
      var result = 0L
      for (i <- 0 until value.length) {
        result = result * seed + value.charAt(i)
      }
      result & (cap - 1)
    }
  }

  def getTodayDate: String = {
    DateFormatUtils.format(new Date(), "yyyy-MM-dd")
  }

}
