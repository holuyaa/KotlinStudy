package com.example.book.deepdive.chapter08

import com.example.util.log
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.job
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    log(coroutineContext.job.isActive)

    val ctx = CoroutineName("test")
    try {
        log(ctx.job.isActive)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}