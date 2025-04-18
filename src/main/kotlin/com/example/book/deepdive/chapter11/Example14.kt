package com.example.book.deepdive.chapter11

import com.example.util.log
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout

private suspend fun test(): Int = withTimeout(1500) {
    delay(1000)
    log("Still thinking")
    delay(1000)
    log("Done!")
    42
}

suspend fun main(): Unit = coroutineScope {
    try {
        test()
    } catch (_: TimeoutCancellationException)  {
        log("Cancelled")
    }
    delay(1000)
}