package com.example.book.deepdive.chapter08

import com.example.util.log
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking(CoroutineName("main")) {
    val name = coroutineContext[CoroutineName]?.name
    log(name.toString())
    launch {
        delay(1000)
        val name = coroutineContext[CoroutineName]?.name
        log(name.toString())
    }
}