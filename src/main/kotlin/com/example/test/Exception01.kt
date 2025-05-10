package com.example.test

import com.example.util.log
import kotlinx.coroutines.*

fun main() = runBlocking {
//    try {
//        coroutineScope {
        supervisorScope {
            val deferred1 = async {
                log("async #1 starts")
                delay(100)
                "Result #1"
            }

            val deferred2 = async {
                log("async #2 starts")
                delay(50)
                throw IllegalArgumentException("An exception happened at async #2")
                "Result #2"
            }

            log("Starts to wait")
            val result1 = deferred1.await()
            log("Result #1: $result1")
            val result2 = deferred2.await()
            log("Result #2: $result2")
        }
        log("coroutineScope completed")
//    } catch (e: IllegalArgumentException) {
//        log("main: ${e.message}")
//    } catch (e: CancellationException) {
//        log("main: ${e.message}")
//    }
}