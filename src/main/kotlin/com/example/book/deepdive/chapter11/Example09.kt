package com.example.book.deepdive.chapter11

import com.example.util.log
import kotlinx.coroutines.*

class ApiException(
    val code: Int,
    message: String,
) : Throwable(message)

private fun getFollowersNumber(): Int =
    throw ApiException(500, "Service exception")

private suspend fun getUserName(): String {
    delay(500)
    return "marcinmoskala"
}

private suspend fun getTweets(): List<Tweet> {
    return listOf(Tweet("Hello, world"))
}

private suspend fun getUserDetails(): Details = coroutineScope {
    val userName = async { getUserName() }
    val followersNumber = async { getFollowersNumber() }
    Details(userName.await(), followersNumber.await())
}

fun main() = runBlocking {
    val details = try {
        getUserDetails()
    } catch (e: ApiException) {
        log("@@@@ " + e.stackTraceToString())
        null
    }
    val tweets = async { getTweets() }
    log("User: $details")
    log("Tweets: ${tweets.await()}")
}