package com.example.book.deepdive.chapter17

import com.example.util.log
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

fun main(): Unit = runBlocking {
    val c1 = Channel<Char>(capacity = 2)
    val c2 = Channel<Char>(capacity = 2)

    launch {
        for (c in 'A'..'H') {
            delay(400)
            select {
                c1.onSend(c) { log("Sent $c to 1") }
                c2.onSend(c) { log("Sent $c to 2") }
            }
        }
    }

    launch {
        while (true) {
            delay(1000)
            val c = select {
                c1.onReceive { "$it from 1" }
                c2.onReceive { "$it from 2" }
            }
            log("Received $c")
        }
    }
}