package com.example.book.deepdive.chapter22

import com.example.util.log
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

private val flow = flow {
    emit("Message1")
    emit("Message2")
}

suspend fun main() {
    flow.onStart { log("Before") }
        .catch { log("Caught $it") }
        .collect { throw MyError() }
}