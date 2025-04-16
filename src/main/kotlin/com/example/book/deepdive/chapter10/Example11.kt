package com.example.book.deepdive.chapter10

import com.example.util.log
import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    val handler =
        CoroutineExceptionHandler { _, exception ->
            log("Caught $exception")
        }
    val scope = CoroutineScope(SupervisorJob() + handler)
    scope.launch {
        delay(1000)
        throw Error("Some error")
    }
    scope.launch {
        delay(2000)
        log("Will be printed")
    }
    delay(3000)
}