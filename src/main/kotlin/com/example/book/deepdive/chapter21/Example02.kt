package com.example.book.deepdive.chapter21

import com.example.util.log
import kotlinx.coroutines.flow.asFlow

suspend fun main() {
    listOf(1, 2, 3, 4, 5)
        // sefOf(1, 2, 3, 4, 5)
        // sequenceOf(1, 2, 3, 4, 5)
        .asFlow()
        .collect { log(it) }
}