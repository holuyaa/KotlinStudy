package com.example.book.deepdive.chapter19

import com.example.util.log
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

suspend fun main() {
    flow { emit("Message 1") }
        .onEach { log(it) }
        .onStart { log("Do something before") }
        .onCompletion { log("Do something after") }
        .catch { emit("Error") }
        .collect { log("Collected $it") }
}