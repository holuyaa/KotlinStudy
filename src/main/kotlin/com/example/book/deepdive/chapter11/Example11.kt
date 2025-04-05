package com.example.book.deepdive.chapter11

import com.example.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main() = runBlocking {
    log("Before")

    supervisorScope {
        launch {
            delay(1000)
            throw Error()
        }
        launch {
            delay(2000)
            log("Done")
        }
    }

    log("After")
}