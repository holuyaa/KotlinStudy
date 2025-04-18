package com.example.book.deepdive.chapter11

import com.example.util.log
import com.example.util.logJob
import kotlinx.coroutines.*

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main(): Unit = coroutineScope {
    coroutineContext.logJob()
    launch {
        coroutineContext.logJob("#1")
        launch {
            coroutineContext.logJob("#2")
            delay(2000)
            log("Will not be printed")
        }
        withTimeout(1000) {
            coroutineContext.logJob("#3")
            launch {
                coroutineContext.logJob("#4")
                delay(1500)
                log("I'm a child")
            }
            delay(1500)
        }
        delay(1500)
        log("Can I print??")
    }
    launch {
        coroutineContext.logJob("#5")
        delay(2000)
        log("Done")
    }
}