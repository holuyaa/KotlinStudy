package com.example.book.deepdive.chapter14

import com.example.util.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@OptIn(ExperimentalCoroutinesApi::class)
private val dispatcher = Dispatchers.IO
    .limitedParallelism(1)

private var counter = 0

fun main() = runBlocking {
    massiveRun {
        withContext(dispatcher) {
            counter++
        }
    }
    log(counter)
}