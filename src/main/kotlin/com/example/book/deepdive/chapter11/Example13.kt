package com.example.book.deepdive.chapter11

import com.example.util.log
import kotlinx.coroutines.*

fun main() = runBlocking {
    log("Before")

    withContext(SupervisorJob()) {
        launch {
            delay(1000)
            throw Error()
        }

        launch {
            delay(2000)
            log("Done")
        }
    }

    log("After")
}