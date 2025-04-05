package com.example.book.deepdive.chapter10

import com.example.util.log
import kotlinx.coroutines.*

@OptIn(ExperimentalCoroutinesApi::class)
fun main(): Unit = runBlocking {
    log("#1 parent: ${coroutineContext.job.parent}, current: ${coroutineContext.job}")
    val job = launch {
        log("#11 parent: ${coroutineContext.job.parent}, current: ${coroutineContext.job}")
        withContext(Dispatchers.Default) {
            log("#111 parent: ${coroutineContext.job.parent}, current: ${coroutineContext.job}")
            launch {
                log("#1111 parent: ${coroutineContext.job.parent}, current: ${coroutineContext.job}")
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