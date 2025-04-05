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

private suspend fun longTask2() = coroutineScope {
    launch {
        launch {
            delay(500)
            val name = coroutineContext[CoroutineName]?.name
            log("[$name] Finished task 1.1")
        }
        throw Error("test task 1")
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

fun main() = runBlocking {
    val job = launch(CoroutineName("Parent")) {
        longTask()
    }
    delay(1500)
    job.cancel()
}
