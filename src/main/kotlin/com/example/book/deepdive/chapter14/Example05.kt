package com.example.book.deepdive.chapter14

import com.example.util.log
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger

private val counter = AtomicInteger()

fun main() = runBlocking {
    massiveRun {
        counter.set(counter.get() + 1)
    }
    log(counter.get())
}
