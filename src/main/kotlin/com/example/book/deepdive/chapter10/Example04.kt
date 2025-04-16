package com.example.book.deepdive.chapter10

import com.example.util.log
import com.example.util.logJob
import kotlinx.coroutines.*

/**
 * SupervisorJobImpl -> coroutine#2 -> coroutine#3
 *                                  -> coroutine#4
 */
fun main(): Unit = runBlocking {
    // Don't do this
    launch(SupervisorJob()) {
        coroutineContext.logJob("#1")
        launch {
            coroutineContext.logJob("#11")
            delay(1000)
            throw Error("Some error")
        }

        launch {
            coroutineContext.logJob("#12")
            delay(2000)
            log("Will not be printed")
        }
    }

    delay(3000)
    log("Done")
}