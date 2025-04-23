package com.example.book.deepdive.chapter14

import com.example.util.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex

suspend fun main() = coroutineScope {
    repeat(5) {
        launch {
            delayAndPrint()
        }
    }
}

private val mutex = Mutex()

private suspend fun delayAndPrint() {
    mutex.lock()
    delay(1000)
    log("Done")
    mutex.unlock()
}
