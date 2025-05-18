package com.example.book.deepdive.chapter22

import com.example.util.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

suspend fun main() = coroutineScope {
    val job = launch {
        flowOf(1, 2)
            .onEach { delay(1000) }
            .onCompletion { log("Completed") }
            .collect { log(it) }
    }
    delay(1100)
    job.cancel()
}