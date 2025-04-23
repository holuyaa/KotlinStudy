package com.example.book.deepdive.chapter14

import com.example.util.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class User(
    val name: String
)

abstract class NetworkService {
    abstract suspend fun fetchUser(id: Int): User
}

class FakeNetworkService : NetworkService() {
    override suspend fun fetchUser(id: Int): User {
        delay(2)
        return User("User$id")
    }
}

private class UserDownloader(
    private val api: NetworkService
) {
    private val users = mutableListOf<User>()

    fun downloaded(): List<User> = users.toList()

    suspend fun fetchUser(id: Int) {
        val newUser = api.fetchUser(id)
        users.add(newUser)
    }
}

suspend fun main() {
    val downloader = UserDownloader(FakeNetworkService())
    coroutineScope {
        repeat(1_000_000) {
            launch {
                downloader.fetchUser(it)
            }
        }
    }
    log(downloader.downloaded().size)
}