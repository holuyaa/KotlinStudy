package com.example.book.deepdive.chapter08

import com.example.util.log
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = Job()

    launch(job) {
        repeat(5) { num ->
            delay(20)
            log("Rep$num")
        }
    }

    launch {
        delay(500)
        job.complete()
    }

    job.join()

    launch(job) {
        log("Will not be printed")
    }

    log("Done")
}