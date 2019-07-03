package com.knoldus.models

import org.apache.spark.sql.types.{ArrayType, IntegerType, LongType, StringType, StructField, StructType}

case class Employee(id: Int, name: String, designation: String, department: List[String], salary: Long, reportingManager: String)

object Employee{
  val schema: StructType = StructType(
    List(
      StructField("id", IntegerType, nullable = true),
      StructField("name", StringType, nullable = true),
      StructField("designation", StringType, nullable = true),
      StructField("department", ArrayType(StringType, true), true),
      StructField("salary", LongType, nullable = true),
      StructField("reportingManager", StringType, nullable = true)
    )
  )
}
