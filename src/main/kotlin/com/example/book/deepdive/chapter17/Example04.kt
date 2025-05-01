package com.example.book.deepdive.chapter17

import com.example.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.selects.select

/**
 * Borrowed from: https://github.com/LouisCAD/Splitties/blob/main/modules/coroutines/src/commonMain/kotlin/splitties/coroutines/Racing.kt
 */
suspend fun <T> raceOf(vararg racers: suspend CoroutineScope.() -> T): T {
    require(racers.isNotEmpty()) { "A race needs racers." }
    return coroutineScope {
        val racersParent = Job(parent = coroutineContext[Job])
        select<T> {
            racers.forEach { racer ->
                async(
                    context = racersParent,
                    start = CoroutineStart.UNDISPATCHED,
                    block = racer
                ).onAwait { resultOfWinner: T ->
                    racersParent.cancel()
                    return@onAwait resultOfWinner
                }
            }
        }
    }
}
private suspend fun requestData1(): String {
    delay(10_000)
    return "Data1"
}

private suspend fun requestData2(): String {
    delay(1000)
    return "Data2"
}

private suspend fun askMultipleForData(): String = raceOf({
    requestData1()
}, {
    requestData2()
})

suspend fun main(): Unit = coroutineScope {
    log(askMultipleForData())
}
