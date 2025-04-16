package com.example.book.deepdive.chapter10

import com.example.util.log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyNonPropagatingException : CancellationException()

suspend fun main(): Unit = coroutineScope {
    launch {  // 1
        launch {  // 2
            delay(2000)
            log("Will not be printed")
        }
        throw MyNonPropagatingException()  // 3
    }
    launch {  // 4
        delay(2000)
        log("Will be printed")
    }
}