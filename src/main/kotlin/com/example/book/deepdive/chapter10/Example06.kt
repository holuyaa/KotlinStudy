package com.example.book.deepdive.chapter10

import com.example.util.log
import com.example.util.logJob
import kotlinx.coroutines.*

/**
 * coroutine#1(BlockingCoroutine) -> coroutine#1(SupervisorCoroutine) -> coroutine#2
 *                                                                    -> coroutine#3
 */
fun main(): Unit = runBlocking {
    coroutineContext.logJob()
    supervisorScope {
        coroutineContext.logJob("#1")
        launch {
            coroutineContext.logJob("#11")
            delay(1000)
            throw Error("Some error")
        }

        launch {
            coroutineContext.logJob("#12")
            delay(2000)
            log("Will be printed")
        }
    }
    delay(1000)
    log("Done")
}