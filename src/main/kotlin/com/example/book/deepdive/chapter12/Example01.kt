package com.example.book.deepdive.chapter12

import com.example.util.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random

suspend fun main() = coroutineScope {
    repeat(1000) {
        launch {
            List(1000) { Random.nextLong()  }.maxOrNull()

            val threadName = Thread.currentThread().name
            log("Running on thread: $threadName")
        }
    }
}