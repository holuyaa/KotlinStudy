package com.example.book.deepdive.chapter10

import com.example.util.log
import kotlinx.coroutines.*

@OptIn(ExperimentalCoroutinesApi::class)
fun main(): Unit = runBlocking {
    log("#0 parent: ${coroutineContext.job.parent}, current: ${coroutineContext.job}")
    supervisorScope {
        log("#1 parent: ${coroutineContext.job.parent}, current: ${coroutineContext.job}")
        launch {
            log("#11 parent: ${coroutineContext.job.parent}, current: ${coroutineContext.job}")
            delay(1000)
            throw Error("Some error")
        }

        launch {
            log("#12 parent: ${coroutineContext.job.parent}, current: ${coroutineContext.job}")
            delay(2000)
            log("Will be printed")
        }
    }
    delay(1000)
    log("Done")
}