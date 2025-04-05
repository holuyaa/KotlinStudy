package com.example.book.deepdive.chapter10

import com.example.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    try {
        launch {
            delay(1000)
            throw Error("Some error")
        }
    } catch (e: Throwable) {
        log("Will not be printed")
    }
    launch {
        delay(2000)
        log("Will not be printed")
    }
}