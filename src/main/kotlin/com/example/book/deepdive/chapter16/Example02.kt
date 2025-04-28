package com.example.book.deepdive.chapter16

import com.example.util.log
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val channel = Channel<Int>()
    launch {
        repeat(5) { index ->
            log("Producing next one")
            delay(1000)
            channel.send(index * 2)
        }
        channel.close()
    }

    launch {
        for (element in channel) {
            log(element)
        }
//        channel.consumeEach { element ->
//            log(element)
//        }
    }
}