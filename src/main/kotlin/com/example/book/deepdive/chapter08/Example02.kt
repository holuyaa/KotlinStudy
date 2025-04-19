package com.example.book.deepdive.chapter08

import com.example.util.log
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() = coroutineScope {
    val job = Job()
    log(job)
    job.complete()
    log(job)

    val activeJob = launch {
        delay(1000)
    }
    log(activeJob)
    activeJob.join()
    log(activeJob)

    val lazyJob = launch(start = CoroutineStart.LAZY) {
        delay(1000)
    }
    log(lazyJob)
    lazyJob.start()
    log(lazyJob)
    log(lazyJob)
}