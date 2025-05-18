package com.example.book.deepdive.chapter22

import com.example.util.log
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

class MyError : Throwable("My error")

private val flow = flow {
    emit(1)
    emit(2)
    throw MyError()
}

suspend fun main() {
    flow.onEach { log("Got $it") }
        .catch { log("Caught $it") }
        .collect { log("Collected $it") }
}