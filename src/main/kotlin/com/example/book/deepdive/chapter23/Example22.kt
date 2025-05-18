package com.example.book.deepdive.chapter23

import com.example.util.log
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce

suspend fun main() {
    val flow = flowOf(1, 2, 3, 4)
        .map { it * it }

    log(flow.first())
    log(flow.count())

    log(flow.reduce { acc, value -> acc * value  })
    log(flow.fold(0) { acc, value -> acc + value  })
}