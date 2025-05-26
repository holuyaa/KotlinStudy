package com.example.book.deepdive.chapter24

import com.example.util.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

suspend fun main(): Unit = coroutineScope {
    val flow = flowOf("A", "B", "C")
        .onEach { delay(1000) }
        .onEach { log("Produced $it") }
    val stateFlow: StateFlow<String> = flow.stateIn(this)

    log("Listening")
    log(stateFlow.value)
    stateFlow.collect { log("Received $it") }
}