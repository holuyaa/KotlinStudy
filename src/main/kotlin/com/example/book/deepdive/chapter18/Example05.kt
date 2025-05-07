package com.example.book.deepdive.chapter18

import com.example.util.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

private fun makeFlow() = flow {
    log("Flow started")
    for (i in 1..3) {
        delay(1000)
        emit(i)
    }
}

suspend fun main() = coroutineScope {
    val flow = makeFlow()

    delay(1000)
    log("Calling flow...")
    flow.collect { value -> log(value) }
    log("Consuming again...")
    flow.collect { value -> log(value) }
}