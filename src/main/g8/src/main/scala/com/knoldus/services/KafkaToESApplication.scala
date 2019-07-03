package com.knoldus.services

import com.knoldus.models.Employee
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, from_json}
import org.elasticsearch.hadoop.cfg.ConfigurationOptions

object KafkaToESApplication extends App {

  val spark = SparkSession
    .builder()
    .appName("From_Kafka_To_ES")
    .config(ConfigurationOptions.ES_NODES, "127.0.0.1")
    .config(ConfigurationOptions.ES_PORT, "9200")
    .master("local[*]")
    .getOrCreate()

  spark.sparkContext.setLogLevel("WARN")

  val df = spark
    .readStream
    .format("kafka")
    .option("kafka.bootstrap.servers","localhost:9092")
    .option("startingOffsets","earliest")
    .option("subscribe","employee")
    .load()

 df.select(col("value"))
    .select(col("value").cast("STRING"))
    .select(from_json(col("value"), Employee.schema).as("Employees_data"))
    .select(col("Employees_data.*"))
    .writeStream
    .outputMode("append")
    .format("org.elasticsearch.spark.sql")
    .option("checkpointLocation","src/main/resources/checkpoints")
    .start("employee_data/full-time-employee").awaitTermination()

}
