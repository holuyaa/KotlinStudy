package com.example.book.deepdive.chapter17

import com.example.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun CoroutineScope.produceString(
    s: String,
    time: Long
) = produce {
    while (true) {
        delay(time)
        send(s)
    }
}

fun main() = runBlocking {
    val fooChannel = produceString("foo", 210L)
    val barChannel = produceString("Bar", 500L)

    repeat(7) {
        select {
            fooChannel.onReceive {
                log("From fooChannel: $it")
            }
            barChannel.onReceive {
                log("From barChannel: $it")
            }
        }
    }

    coroutineContext.cancelChildren()
}