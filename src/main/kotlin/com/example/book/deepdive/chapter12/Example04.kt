package com.example.book.deepdive.chapter12

import com.example.util.log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main(): Unit = coroutineScope {
    launch {
        printCoroutineTime(Dispatchers.IO)
    }
    launch {
        val dispatcher = Dispatchers.IO
            .limitedParallelism(100)
        printCoroutineTime(dispatcher)
    }
}

private suspend fun printCoroutineTime(
    dispatcher: CoroutineDispatcher
) {
    val test = measureTimeMillis {
        coroutineScope {
            repeat(100) {
                launch(dispatcher) {
                    Thread.sleep(1000)
                }
            }
        }
    }
    log("$dispatcher took: $test")
}