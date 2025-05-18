package com.example.book.deepdive.chapter22

import com.example.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

suspend fun main() {
    flowOf(1, 2)
        .onEach { delay(1000) }
        .onStart { log("Before") }
        .collect { log(it) }
}