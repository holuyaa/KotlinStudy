package com.example.book.deepdive.chapter11

import com.example.util.log
import kotlinx.coroutines.*

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main(): Unit = coroutineScope {
    log("#0 parentJob: ${coroutineContext.job.parent}, me: ${coroutineContext.job}")
    launch {
        log("#1 parentJob: ${coroutineContext.job.parent}, me: ${coroutineContext.job}")
        launch {
            log("#2 parentJob: ${coroutineContext.job.parent}, me: ${coroutineContext.job}")
            delay(2000)
            log("Will not be printed")
        }
        withTimeout(1000) {
            log("#3 parentJob: ${coroutineContext.job.parent}, me: ${coroutineContext.job}")
            launch {
                log("#4 parentJob: ${coroutineContext.job.parent}, me: ${coroutineContext.job}")
                delay(1500)
                log("I'm a child")
            }
            delay(1500)
        }
        delay(1500)
        log("Can I print??")
    }
    launch {
        log("#5 parentJob: ${coroutineContext.job.parent}, me: ${coroutineContext.job}")
        delay(2000)
        log("Done")
    }
}