package com.example.book.deepdive.chapter10

import com.example.util.log
import com.example.util.logJob
import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    coroutineContext.logJob("#1")
    val job = launch {
        coroutineContext.logJob("#11")
        withContext(Dispatchers.Default) {
            coroutineContext.logJob("#111")
            launch {
                coroutineContext.logJob("#1111")
                delay(1000)
                throw Error("Some error")
            }
            delay(2000)
            log("Will not be printed")
        }
    }
    job.join()
    log("Done")
}