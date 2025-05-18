package com.example.book.deepdive.chapter23

import com.example.util.log
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.drop

suspend fun main() {
    ('A'..'Z').asFlow()
        .drop(20)
        .collect { log(it) }
}