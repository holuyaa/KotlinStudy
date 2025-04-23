package com.example.book.deepdive.chapter14

import com.example.util.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicReference
import kotlin.system.measureTimeMillis

private class UserDownloader2(
    private val api: NetworkService
) {
    private val users = AtomicReference(listOf<User>())

    fun downloaded(): List<User> = users.get()

    suspend fun fetchUser(id: Int) {
        val newUser = api.fetchUser(id)
        users.getAndUpdate { it + newUser }
    }
}

suspend fun main() {
    val downloader = UserDownloader2(FakeNetworkService())
    val duration = measureTimeMillis {
        coroutineScope {
            // It takes long time to do the below code. That's why I changed from 1_000_000 to 100_000.
            repeat(100_000) {
                launch {
                    downloader.fetchUser(it)
                }
            }
        }
    }
    log("count = ${downloader.downloaded().size}, duration: $duration")
}
