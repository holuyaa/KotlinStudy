package com.example.book.deepdive.chapter22

import com.example.util.log
import kotlinx.coroutines.flow.flow

private val flow = flow {
    emit("Message1")
    throw MyError()
}

suspend fun main() {
    try {
        flow.collect { log("Collected $it") }
    } catch (_: MyError) {
        log("Caught")
    }
}