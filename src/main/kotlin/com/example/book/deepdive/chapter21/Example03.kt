package com.example.book.deepdive.chapter21

import com.example.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow

suspend fun main() {
    val function = suspend {
        delay(1000)
        "UserName"
    }

    function.asFlow()
        .collect { log(it) }
}