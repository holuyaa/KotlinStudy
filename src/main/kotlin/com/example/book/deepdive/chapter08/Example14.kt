package com.example.book.deepdive.chapter08

import com.example.util.log
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val parentJob = Job()
    val job = Job(parentJob)
    launch(job) {
        delay(1000)
        log("Text 1")
    }
    launch(job) {
        delay(2000)
        log("Text 2")
    }
    delay(1000)
    parentJob.cancel()
    job.children.forEach { it.join() }
}