package com.example.book.deepdive.chapter14

import com.example.util.log
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

private var counter = 0

fun main() = runBlocking {
    val lock = Any()
    val duration = measureTimeMillis {
        massiveRun {
            synchronized(lock) {
                counter++
            }
        }
    }
    log("count = ${counter}, duration: $duration")
}