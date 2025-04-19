package com.example.book.deepdive.chapter08

import com.example.util.log
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = Job()

    launch(job) {
        delay(1000)
        log("Text 1")
    }

    launch(job) {
        delay(2000)
        log("Text 2")
    }

    job.complete()
    job.join()
}
