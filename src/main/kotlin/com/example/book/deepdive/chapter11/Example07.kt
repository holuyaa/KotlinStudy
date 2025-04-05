package com.example.book.deepdive.chapter11

import com.example.util.log
import kotlinx.coroutines.*

private suspend fun longTask() = coroutineScope {
    launch {
        delay(1000)
        val name = coroutineContext[CoroutineName]?.name
        log("[$name] Finished task 1")
    }
    launch {
        delay(2000)
        val name = coroutineContext[CoroutineName]?.name
        log("[$name] Finished task 2")
    }
}

fun main() = runBlocking(CoroutineName("Parent")) {
    log("Before")
    longTask()
    log("After")
}