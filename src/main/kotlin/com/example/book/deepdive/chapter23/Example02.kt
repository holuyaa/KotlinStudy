package com.example.book.deepdive.chapter23

import com.example.util.log
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter

private fun isEven(num: Int): Boolean = num % 2 == 0

suspend fun main() {
    (1..10).asFlow()
        .filter { it <= 5 }
        .filter { isEven(it) }
        .collect { log(it) }
}