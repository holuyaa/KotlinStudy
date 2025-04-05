package com.example.book.deepdive.chapter10

import com.example.util.log
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val job = SupervisorJob()
    launch(job) {
        delay(1000)
        throw Error("Some error")
    }
    launch(job) {
        delay(2000)
        log("Will be printed")
    }
    job.join()
}