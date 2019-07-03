package com.knoldus.services

import com.knoldus.models.Employee
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object KafkaToKafkaApplication extends App {

  val spark = SparkSession.builder().appName("From_Kafka_To_Kafka").master("local[*]").getOrCreate()
  spark.sparkContext.setLogLevel("WARN")

  val df = spark
    .readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("startingOffsets", "earliest")
    .option("subscribe", "employee")
    .load()

  df.select(to_json(from_json(col("value").cast("STRING"), Employee.schema)).as("value"))
    .writeStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("checkpointLocation", "src/main/resources/checkpoints")
    .option("topic", "result")
    .start().awaitTermination()
}
