package com.example.book.deepdive.chapter18

import com.example.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

@OptIn(ExperimentalCoroutinesApi::class)
private fun CoroutineScope.makeChannel() = produce {
    log("Channel started")
    for (i in 1..3) {
        delay(1000)
        send(i)
    }
}

suspend fun main() = coroutineScope {
    val channel = makeChannel()

    delay(1000)
    log("Calling channel...")
    for (value in channel) {
        log(value)
    }
    log("Consuming again...")
    for (value in channel) {
        log(value)
    }
}