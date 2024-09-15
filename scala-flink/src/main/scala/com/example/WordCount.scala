package com.example
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._

object WordCount {
  def main(args: Array[String]): Unit = {
    // Set up the execution environment
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment

    // Input data (static)
    val text = env.fromElements(
      "Apache Flink is a framework and distributed processing engine",
      "Flink provides batch processing and data stream processing",
      "Word count example using Flink"
    )

    // Transform the input data
    val wordCounts = text
      .flatMap(_.toLowerCase.split("\\W+"))  // Split lines into words
      .map((_, 1))                           // Convert words to (word, 1)
      .groupBy(0)                            // Group by the word
      .sum(1)                                // Sum the occurrences

    // Print the result
    wordCounts.print()
  }
}
