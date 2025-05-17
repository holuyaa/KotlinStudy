package com.example.book.deepdive.chapter21

import com.example.util.log
import kotlinx.coroutines.flow.flowOf

suspend fun main() {
    flowOf(1, 2, 3, 4, 5)
        .collect { log(it) }
}