package com.example.book.deepdive.chapter22

import com.example.util.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

suspend fun main(): Unit = coroutineScope {
    flowOf("User1", "User2")
        .onStart { log("Users:") }
        .onEach { log(it) }
        .launchIn(this)
}