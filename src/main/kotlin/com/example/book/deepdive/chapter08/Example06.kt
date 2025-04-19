package com.example.book.deepdive.chapter08

import com.example.util.log
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    launch(Job()) {
        delay(1000)
        log("Will not be printed")
    }
}