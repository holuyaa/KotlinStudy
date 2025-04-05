package com.example.book.deepdive.chapter11

import com.example.util.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    log("start")
    val a = coroutineScope {
        delay(1000)
        10
    }
    log("a is calculated")
    val b = coroutineScope {
        delay(1000)
        20
    }
    log(a)
    log(b)
}