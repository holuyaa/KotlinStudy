package com.example.book.deepdive.chapter21

import com.example.util.log
import io.reactivex.Flowable
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactive.asFlow

suspend fun main() = coroutineScope {
    Flowable.range(1, 5).asFlow()
        .collect { log(it) }
}