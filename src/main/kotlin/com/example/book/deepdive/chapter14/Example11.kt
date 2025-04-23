package com.example.book.deepdive.chapter14

import com.example.util.log
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.system.measureTimeMillis

private val mutex = Mutex()

private var counter = 0

fun main() = runBlocking {
    val duration = measureTimeMillis {
        massiveRun {
            mutex.withLock {
                counter++
            }
        }
    }
    log("count = ${counter}, duration: $duration")
}