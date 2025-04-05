package com.example.book.deepdive.chapter11

import com.example.util.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.coroutines.yield

class User

private suspend fun fetchUser(): User {
    while (true) {
        yield()
    }
}

suspend fun getUserOrNull(): User? =
    withTimeoutOrNull(5000) {
        fetchUser()
    }

suspend fun main(): Unit = coroutineScope {
    val user = getUserOrNull()
    log("User: $user")
}