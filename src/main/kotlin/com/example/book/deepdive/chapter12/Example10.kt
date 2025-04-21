package com.example.book.deepdive.chapter12

import com.example.util.log
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

@OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
suspend fun main(): Unit = withContext(newSingleThreadContext("Thread1")) {
    var continuation: Continuation<Unit>? = null

    launch(newSingleThreadContext("Thread2")) {
        delay(1000)
        continuation?.resume(Unit)
    }

    launch(Dispatchers.Unconfined) {
        log("@@@@ #1")

        suspendCancellableCoroutine {
            continuation = it
        }

        log("@@@@ #2")

        delay(1000)

        log("@@@@ #3")  // kotlinx.coroutines.DefaultExecutor which is used by delay()
    }
}