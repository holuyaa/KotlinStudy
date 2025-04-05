package com.example.book.deepdive.chapter11

import com.example.util.log
import kotlinx.coroutines.*

@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.log2(text: String) {
    val job = this.coroutineContext.job
    val parentJob = job.parent
    log("[$parentJob] [$job] $text")
}

fun main() = runBlocking(CoroutineName("Parent")) {
    log2("Before")

    withContext(CoroutineName("Child 1")) {
        delay(1000)
        log2("Hello 1")
    }

    withContext(CoroutineName("Child 2")) {
        delay(1000)
        log2("Hello 2")
    }

    log2("After")
}