package com.example.book.deepdive.chapter16

import com.example.util.log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main(): Unit = coroutineScope {
    val channel = Channel<Int>(
        capacity = 2,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    launch {
        repeat(5) { index ->
            channel.send(index * 2)
            delay(100)
            log("Sent")
        }
        channel.close()
    }

    delay(1000)
    for (element in channel) {
        log(element)
        delay(1000)
    }
}