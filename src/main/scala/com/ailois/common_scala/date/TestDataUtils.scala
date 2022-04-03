package com.ailois.common_scala.date

import java.time.format.DateTimeFormatter
import java.time.{LocalDate, ZoneId}
import java.util.{Date, TimeZone}

import org.apache.commons.lang3.time.DateFormatUtils
import org.apache.http.client.utils.DateUtils

object TestDataUtils {

  def main(args: Array[String]): Unit = {

    println(DateUtils.formatDate(new Date(1593592660761L), "yyyy-MM-dd HH:mm:ss"))
    println(getStartOfToday)
    println(DateUtils.parseDate(getStartOfToday))

    print(getNowHour)

  }

  def getStartOfToday: String = LocalDate.now.atStartOfDay(ZoneId.systemDefault).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

  def getNowHour: String = DateFormatUtils.format(new Date(), "yyyy-MM-dd'T'HH", TimeZone.getTimeZone("Asia/Shanghai"))

}
