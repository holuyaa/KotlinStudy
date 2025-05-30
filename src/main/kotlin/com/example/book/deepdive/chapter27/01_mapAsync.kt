package com.example.book.deepdive.chapter27

import com.example.util.log
import com.example.util.logJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

private suspend fun <T, R> List<T>.mapAsync(
    transform: suspend (T) -> R
): List<R> = coroutineScope {
    coroutineContext.logJob("mapAsync")
    this@mapAsync.map { async { transform(it)} }
        .awaitAll()
}

suspend fun main(): Unit = coroutineScope {
    coroutineContext.logJob("parent")
    val avg = (0 until 10).toList()
        .mapAsync {
            delay(1000)
            it * it
        }.average()
    log(avg)
}