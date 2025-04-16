package com.example.book.deepdive.chapter11

import com.example.util.log
import com.example.util.logJob
import kotlinx.coroutines.*

fun main() = runBlocking {
    log("Before")

    withContext(SupervisorJob()) {
        coroutineContext.logJob("#1")
        launch {
            coroutineContext.logJob("#11")
            delay(1000)
            throw Error()
        }

        launch {
            coroutineContext.logJob("#12")
            delay(2000)
            log("Done")
        }
    }

    log("After")
}