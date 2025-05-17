package com.example.book.deepdive.chapter21

import com.example.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

data class User(
    val name: String
)

interface UserApi {
    suspend fun takePage(pageNumber: Int): List<User>
}

class FakeUserApi : UserApi {
    private val users = List(20) { User("User$it") }
    private val pageSize: Int = 3

    override suspend fun takePage(pageNumber: Int): List<User> {
        delay(1000)
        return users
            .drop(pageSize * pageNumber)
            .take(pageSize)
    }
}

private fun allUsersFlow(
    api: UserApi
): Flow<User> = flow {
    var page = 0
    do {
        log("Fetching page $page")
        val users = api.takePage(page++)
        emitAll(users.asFlow())
    } while (!users.isEmpty())
}

suspend fun main() {
    val api = FakeUserApi()
    val users = allUsersFlow(api)
    val user = users
//        .buffer()
        .first {
            log("Checking $it")
            delay(1000)
            it.name == "User3"
        }
    log(user)
}