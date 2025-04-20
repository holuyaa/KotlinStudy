package com.example.book.deepdive.chapter12

import com.example.util.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main() = coroutineScope {
    val dispatcher = Dispatchers.Default
        .limitedParallelism(5)
    repeat(1000) {
        launch(dispatcher) {
            List(1000) { Random.nextLong()  }.maxOrNull()

            val threadName = Thread.currentThread().name
            log("Running on thread: $threadName")
        }
    }
}
