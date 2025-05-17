package com.example.book.deepdive.chapter21

import com.example.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow

suspend fun getUserName(): String {
    delay(1000)
    return "UserName"
}

suspend fun main() {
    ::getUserName
        .asFlow()
        .collect { log(it) }
}