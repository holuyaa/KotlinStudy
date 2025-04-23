package com.example.book.deepdive.chapter14

import com.example.util.log
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

suspend fun main() {
    val mutex = Mutex()
    log("Started")
    mutex.withLock {
        mutex.withLock {
            log("Will never be printed")
        }
    }
}