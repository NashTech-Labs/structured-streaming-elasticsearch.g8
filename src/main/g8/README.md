# Structured-streaming-elasticsearch

A Giter8 template for Structured Streaming with Elasticsearch. It is a simple application to show how we can stream data from Kafka to Elasticsearch.

Simply run: **sbt new knoldus/structured-streaming-elasticsearch.g8**


This project has following applications

1. KafkaToESApplication

2. KafkaToKafkaApplication

***
### 1. KafkaToESApplication
This application provides a template for a **Structured Streaming**
application which **reads data from a Kafka topic** and **insert that
data into an Elasticsearch index**

#### To run this application:

**sbt "runMain com.knoldus.services.KafkaToESApplication"**

***

### 2. KafkaToKafkaApplication
This application provides a template for a **Structured Streaming**
application which **reads data from a Kafka topic** and **puts that
data into sink Kafka topic**

#### To run this application:

**sbt "runMain com.knoldus.services.KafkaToKafkaApplication"**

***


**Prerequisite**: 
 1. Kafka
 2. ElasticSearch 

