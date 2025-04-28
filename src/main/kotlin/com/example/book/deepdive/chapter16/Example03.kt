package com.example.book.deepdive.chapter16

import com.example.util.log
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

suspend fun main(): Unit = coroutineScope {
    val channel = produce {
        repeat(5) { index ->
            log("Producing next one")
            delay(1000)
            send(index * 2)
        }
    }

    for (element in channel) {
        log(element)
    }
}