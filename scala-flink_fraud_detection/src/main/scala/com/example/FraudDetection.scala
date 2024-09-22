package com.example
import org.apache.flink.streaming.api.scala._
import org.apache.flink.api.common.state.{ValueState, ValueStateDescriptor}
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.util.Collector

import main.scala.com.Model.Transaction

object FraudDetection {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    // Create a stream from socket (e.g., listen to localhost on port 9999)
    val textStream: DataStream[String] = env.socketTextStream("host.docker.internal", 9999)
    //  so why this working even after commenting 9999:9999
    // The Flink job inside the Docker container is not listening directly on port 9999 on the container. 
    // Instead, it is reaching out to the host machine’s port 9999 using the host.docker.internal address.
    // Where Is the Service Running?
    // The service that is feeding data to port 9999 is likely running on your host machine (Windows), 
    // not inside the Docker container.
    // Since the service is running on your host machine, you don't need the Docker container to expose port 9999 to 
    // the host machine. Instead, the Flink job inside the container connects directly to the host's service 
    // at host.docker.internal:9999.

    // Parse the input stream, assuming format "amount accountId"
    val transactionStreamRaw: DataStream[(Int, String)] = textStream
      .map { line =>
        val parts = line.split("\\s+")
        val amount = parts(0).toInt      // First part is the amount (e.g., 100)
        val accountId = parts(1)   // Second part is the account ID (e.g., 1)
        (amount, accountId)
      }

    val transactionStream = transactionStreamRaw.map( record => {
      Transaction(Option(record._1), Option(record._2))
    })

    // Key by accountId to process each account's transactions independently
    val keyedStream: KeyedStream[Transaction, String] = transactionStream.keyBy(record => record.accountId.get)

    // Apply a custom KeyedProcessFunction to detect two transactions > 10 within 5 seconds
    val resultStream = keyedStream.process(new TransactionProcessFunction)

    // Print results
    resultStream.print()

    // Execute the Flink job
    env.execute("Transaction Alert Example")
  }

  // Custom KeyedProcessFunction to detect two transactions > 10 within 5 seconds
  
}
