package com.example.book.deepdive.chapter23

import com.example.util.log
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.take

suspend fun main() {
    ('A'..'Z').asFlow()
        .take(5)
        .collect { log(it) }
}