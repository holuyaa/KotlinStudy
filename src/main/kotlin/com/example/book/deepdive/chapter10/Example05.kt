package com.example.book.deepdive.chapter10

import com.example.util.log
import com.example.util.logJob
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * coroutine#1 (BlockingCoroutine)
 * SupervisorJobImp -> coroutine#2
 *                  -> coroutine#3
 */
fun main(): Unit = runBlocking {
    val job = SupervisorJob()
    coroutineContext.logJob()
    job.logJob()
    launch(job) {
        coroutineContext.logJob("#1")
        delay(1000)
        throw Error("Some error")
    }
    launch(job) {
        coroutineContext.logJob("#2")
        delay(2000)
        log("Will be printed")
    }
    job.join()
}