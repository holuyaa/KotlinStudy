package com.example.book.deepdive.chapter08

import com.example.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val job1 = launch {
        delay(1000)
        log("Test1")
    }
    val job2 = launch {
        delay(2000)
        log("Test2")
    }

    job1.join()
    job2.join()
    log("All tests are done")
}