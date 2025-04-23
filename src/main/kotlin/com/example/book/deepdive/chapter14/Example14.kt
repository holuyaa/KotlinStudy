package com.example.book.deepdive.chapter14

import com.example.util.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

class MessageRepository2 {
    private val messages = mutableListOf<String>()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = Dispatchers.IO
        .limitedParallelism(1)

    suspend fun add(message: String) = withContext(dispatcher) {
        delay(1000)
        messages.add(message)
    }
}

suspend fun main() {
    val repo = MessageRepository2()

    val timeMillis = measureTimeMillis {
        coroutineScope {
            repeat(5) {
                launch {
                    repo.add("Message$it")
                }
            }
        }
    }
    log(timeMillis)
}
