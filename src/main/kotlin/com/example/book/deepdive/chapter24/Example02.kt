package com.example.book.deepdive.chapter24

import com.example.util.log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main(): Unit = coroutineScope {
    val mutableSharedFlow = MutableSharedFlow<String>(
        replay = 2
    )
    mutableSharedFlow.emit("Message1")
    mutableSharedFlow.emit("Message2")
    mutableSharedFlow.emit("Message3")

    log(mutableSharedFlow.replayCache)

    launch {
        mutableSharedFlow.collect {
            log("#1 received $it")
        }
    }

    delay(100)
    mutableSharedFlow.resetReplayCache()
    log(mutableSharedFlow.replayCache)
}