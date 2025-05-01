package com.example.book.deepdive.chapter17

import com.example.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.selects.select

private suspend fun requestData1(): String {
    delay(10_000)
    return "Data1"
}

private suspend fun requestData2(): String {
    delay(1000)
    return "Data2"
}

private val scope = CoroutineScope(SupervisorJob())

private suspend fun askMultipleForData(): String = coroutineScope {
    select {
        async { requestData1() }.onAwait { it }
        async { requestData2() }.onAwait { it }
    }
}

suspend fun main(): Unit = coroutineScope {
    log(askMultipleForData())
}
