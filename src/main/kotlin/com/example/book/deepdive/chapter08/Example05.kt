package com.example.book.deepdive.chapter08

import com.example.util.log
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val job: Job = launch {
        delay(1000)
    }

    val parentJob: Job = coroutineContext.job
    log(job == parentJob)
    val parentChildren: Sequence<Job> = parentJob.children
    log(parentChildren.first() == job)
}