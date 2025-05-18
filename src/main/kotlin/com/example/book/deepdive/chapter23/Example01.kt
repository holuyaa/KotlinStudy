package com.example.book.deepdive.chapter23

import com.example.util.log
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

suspend fun main() {
    flowOf(1, 2, 3)
        .map { it * it }
        .collect { log(it) }
}