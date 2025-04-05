package com.example.book.deepdive.chapter10

import com.example.util.log
import kotlinx.coroutines.*

@OptIn(ExperimentalCoroutinesApi::class)
fun main(): Unit = runBlocking {
    val scope = CoroutineScope(SupervisorJob())
    log("#1 parent: ${scope.coroutineContext.job.parent}, current: ${scope.coroutineContext.job}")

    scope.launch {
        log("#2 parent: ${coroutineContext.job.parent}, current: ${coroutineContext.job}")
        launch {
            log("#21 parent: ${coroutineContext.job.parent}, current: ${coroutineContext.job}")
            delay(2000)
            log("#21 Will not be printed")
        }
        launch {
            log("#22 parent: ${coroutineContext.job.parent}, current: ${coroutineContext.job}")
            launch {
                log("#221 parent: ${coroutineContext.job.parent}, current: ${coroutineContext.job}")
                delay(1000)
                throw Error("Some error")
            }
            delay(2000)
            log("#22 Will not be printed")
        }
        launch {
            log("#23 parent: ${coroutineContext.job.parent}, current: ${coroutineContext.job}")
            delay(2000)
            log("#23 Will not be printed")
        }
    }

    scope.launch {
        log("#3 parent: ${coroutineContext.job.parent}, current: ${coroutineContext.job}")
        delay(2000)
        log("#3 Will be printed")
    }

    delay(3000)
    log("Done")
}