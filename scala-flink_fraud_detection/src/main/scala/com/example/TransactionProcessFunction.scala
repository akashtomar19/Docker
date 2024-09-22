package com.example
import main.scala.com.Model._
import org.apache.flink.streaming.api.scala._
import org.apache.flink.api.common.state.{ValueState, ValueStateDescriptor}
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.util.Collector
class TransactionProcessFunction extends KeyedProcessFunction[String, Transaction, String] {

    // State to track the count of transactions over 10 for each account
    lazy val transactionCountState: ValueState[Int] = getRuntimeContext.getState(
      new ValueStateDescriptor[Int]("transactionCount", classOf[Int])
    )

    // State to track if a timer has been set
    lazy val timerState: ValueState[Long] = getRuntimeContext.getState(
      new ValueStateDescriptor[Long]("timerState", classOf[Long])
    )

    override def processElement(
        transaction: Transaction, 
        ctx: KeyedProcessFunction[String, Transaction, String]#Context,
        out: Collector[String]
    ): Unit = {
      val amount = transaction.amount.get
      val accountId = transaction.accountId.get

      // Only consider transactions with amount > 10
      if (amount > 10) {
        val currentCount = transactionCountState.value()

        // If no timer is set, register a 5-second timer
        if (currentCount == 0) {
          val timer = ctx.timerService().currentProcessingTime() + 5000 // 5 seconds timer
          ctx.timerService().registerProcessingTimeTimer(timer)
          timerState.update(timer)
        }

        // Increment the count of transactions over 10
        transactionCountState.update(currentCount + 1)

        // If count reaches 2, trigger an alert
        if (transactionCountState.value() >= 2) {
          out.collect(s"ALERT: Two transactions over 10 for account $accountId within 5 seconds!")
          // Reset the state and timer after triggering the alert
          transactionCountState.clear()
          ctx.timerService().deleteProcessingTimeTimer(timerState.value())
          timerState.clear()
        }
      }
    }

    // This method is called when the 5-second timer expires
    override def onTimer(
        timestamp: Long, 
        ctx: KeyedProcessFunction[String, Transaction, String]#OnTimerContext,
        out: Collector[String]
    ): Unit = {
      // Clear the transaction count and the timer state when the timer expires
      transactionCountState.clear()
      timerState.clear()
    }
  }