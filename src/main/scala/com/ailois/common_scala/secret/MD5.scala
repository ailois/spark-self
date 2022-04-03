package com.ailois.common_scala.secret

import java.math.BigInteger
import java.security.MessageDigest

object MD5 {

  def main(args: Array[String]): Unit = {
    println(getMD5("e6efff3d977f3f71"))
  }

  def getMD5(str: String): String = {
    val md5 = MessageDigest.getInstance("MD5")
    val array = md5.digest(str.getBytes("UTF-8"))
    val bigInt = new BigInteger(1, array)
    bigInt.toString(10)
  }


}
