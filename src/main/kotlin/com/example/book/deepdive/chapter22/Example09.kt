package com.example.book.deepdive.chapter22

import com.example.util.log
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach

private val flow = flow {
   emit("Message1")
    throw MyError()
}

suspend fun main() {
    flow.catch { emit("Error") }
        .collect { log("Collected $it") }

    flowOf("Message1")
        .catch { emit("Error") }
        .onEach { throw Error(it) }
        .collect { log("Collected $it")  }
}