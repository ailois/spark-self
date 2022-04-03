package com.ailois.common_scala.collection

object TestPar {

  def main(args: Array[String]): Unit = {
    (1 to 2).par.foreach(println(_))
  }

}
