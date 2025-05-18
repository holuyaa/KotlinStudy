package com.example.book.deepdive.chapter22

import com.example.util.log
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach

suspend fun main() {
    flowOf(1, 2, 3, 4)
        .onEach { log(it) }
        .collect {}
}