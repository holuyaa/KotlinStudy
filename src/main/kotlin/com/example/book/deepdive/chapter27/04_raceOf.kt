package com.example.book.deepdive.chapter27

import com.example.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.selects.select

private suspend fun <T> raceOf(
    racer: suspend CoroutineScope.() -> T,
    vararg racers: suspend CoroutineScope.() -> T
): T = coroutineScope {
    select {
        (listOf(racer) + racers).forEach { racer ->
            async { racer() }.onAwait {
                coroutineContext.job.cancelChildren()
                it
            }
        }
    }
}

private suspend fun a(): String {
    delay(1000)
    return "A"
}

private suspend fun b(): String {
    delay(2000)
    return "B"
}

private suspend fun c(): String {
    delay(3000)
    return "C"
}

suspend fun main(): Unit = coroutineScope {
    log(raceOf({ c() }))
    log(raceOf({ b() }, { a() }))
    log(raceOf({ b() }, { c() }))
    log(raceOf({ b() }, { a() }, { c() }))
}
