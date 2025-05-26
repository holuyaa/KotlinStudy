package com.example.book.deepdive.chapter24

import com.example.util.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val state = MutableStateFlow('X')

    launch {
        for (c in 'A'..'E') {
            delay(300)
            state.value = c
        }
    }

    state.collect {
        delay(1000)
        log(it)
    }
}