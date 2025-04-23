package com.example.book.deepdive.chapter14

import com.example.util.log
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

private val counter = AtomicInteger()

fun main() = runBlocking {
    val duration = measureTimeMillis {
        massiveRun {
            counter.incrementAndGet()
        }
    }
    log("count = ${counter.get()}, duration: $duration")
}