package com.example.book.deepdive.chapter12

import com.example.util.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private var i = 0

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main(): Unit = coroutineScope {
//    old way 1
//    val dispatcher = newSingleThreadContext("My name")
//
//    old way 2
//    val dispatcher = Executors.newSingleThreadExecutor()
//        .asCoroutineDispatcher()

    val dispatcher = Dispatchers.Default
        .limitedParallelism(1)

    repeat(10_000) {
        launch(dispatcher) {
            i++
        }
    }
    delay(1000)
    log(i)
}
