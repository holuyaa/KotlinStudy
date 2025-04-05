package com.example.book.deepdive.chapter10

import com.example.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    launch {
        launch {
            delay(1000)
            throw Error("Some error")
        }

        launch {
            delay(2000)
            log("Will not be printed")
        }

        launch {
            delay(500)
            log("Will be printed")
        }

        launch {
            delay(2000)
            log("Will not be printed")
        }
    }
}