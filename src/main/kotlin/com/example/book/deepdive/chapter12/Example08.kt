package com.example.book.deepdive.chapter12

import com.example.util.log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

private val LoomDispatcher = Executors
    .newVirtualThreadPerTaskExecutor()
    .asCoroutineDispatcher()

private object LoomDispatcher2 : ExecutorCoroutineDispatcher() {
    override val executor: Executor = Executor { command ->
        Thread.startVirtualThread(command)
    }

    override fun dispatch(
        context: CoroutineContext,
        block: Runnable
    ) {
        executor.execute(block)
    }

    override fun close() {
        error("Cannot be invoked on Dispatchers.LOOM")
    }
}

private val Dispatchers.Loom: CoroutineDispatcher
    get() = LoomDispatcher

suspend fun main() = measureTimeMillis {
    coroutineScope {
        repeat(100_000) {
            launch(Dispatchers.Loom) {
                Thread.sleep(1000)
            }
        }
    }
}.let(::log)