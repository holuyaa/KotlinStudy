package com.example.book.deepdive.chapter10

import com.example.util.log
import com.example.util.logJob
import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    val scope = CoroutineScope(SupervisorJob())
    scope.coroutineContext.logJob()

    scope.launch {
        coroutineContext.logJob("#1")
        launch {
            coroutineContext.logJob("#11")
            delay(2000)
            log("#11 Will not be printed")
        }
        launch {
            coroutineContext.logJob("#12")
            launch {
                coroutineContext.logJob("#121")
                delay(1000)
                throw Error("Some error")
            }
            delay(2000)
            log("#12 Will not be printed")
        }
        launch {
            coroutineContext.logJob("#13")
            delay(2000)
            log("#13 Will not be printed")
        }
    }

    scope.launch {
        coroutineContext.logJob("#2")
        delay(2000)
        log("#2 Will be printed")
    }

    delay(3000)
    log("Done")
}