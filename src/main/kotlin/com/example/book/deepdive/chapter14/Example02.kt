package com.example.book.deepdive.chapter14

import com.example.util.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private var counter = 0

fun main() = runBlocking {
    massiveRun {
        counter++
    }
    log(counter)
}

suspend fun massiveRun(action: suspend () -> Unit) {
    withContext(Dispatchers.Default) {
        repeat(1000) {
            launch {
                repeat(1000) {
                    action()
                }
            }
        }
    }
}