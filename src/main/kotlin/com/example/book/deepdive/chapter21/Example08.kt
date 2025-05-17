package com.example.book.deepdive.chapter21

import com.example.util.log
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first

// This is similar to using buffer() function before invoking first()
// However, launch{} can be used inside of channelFlow unlike flow {} builder
private fun allUsersFlow(api: UserApi): Flow<User> = channelFlow {
    var page = 0
    do {
        val users = async {
            log("Fetching page $page")
            api.takePage(page++).apply {
                forEach { send(it) }
            }
        }.await()
    } while (!users.isEmpty())
}

suspend fun main() {
    val api = FakeUserApi()
    val users = allUsersFlow(api)
    val user = users
        .first {
            log("Checking $it")
            delay(1000)
            it.name == "User3"
        }
    log(user)
}