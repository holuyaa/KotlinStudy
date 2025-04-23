package com.example.book.deepdive.chapter12

import com.example.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import kotlin.random.Random
import kotlin.sequences.forEach

val executor = Executors.newScheduledThreadPool(16)

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main(): Unit = coroutineScope {
    val dispatcher = Dispatchers.IO
        .limitedParallelism(5)
    val job = Job()
    val scope = CoroutineScope(job + dispatcher)
    repeat(1000) {
        executor.submit {
            scope.launch {
                List(1000) { Random.nextLong() }.maxOrNull()

                val threadName = Thread.currentThread().name
                log("Running on thread: $threadName")
            }
        }
    }
    job.children.forEach { it.join() }
}
