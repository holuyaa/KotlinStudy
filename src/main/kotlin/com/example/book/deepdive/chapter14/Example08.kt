package com.example.book.deepdive.chapter14

import com.example.util.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

class UserDownloader3(
    private val api: NetworkService
) {
    private val users = mutableListOf<User>()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = Dispatchers.IO
        .limitedParallelism(1)

    suspend fun downloaded(): List<User> =
        withContext(dispatcher) {
            users.toList()
        }

    suspend fun fetchUser(id: Int) = withContext(dispatcher) {
        val newUser = api.fetchUser(id)
        users += newUser
    }
}

suspend fun main() {
    val downloader = UserDownloader3(FakeNetworkService())
    val duration = measureTimeMillis {
        coroutineScope {
            repeat(1_000_000) {
                launch {
                    downloader.fetchUser(it)
                }
            }
        }
    }
    log("count = ${downloader.downloaded().size}, duration: $duration")
}
