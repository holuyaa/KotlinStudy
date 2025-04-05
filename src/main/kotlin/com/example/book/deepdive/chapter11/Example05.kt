package com.example.book.deepdive.chapter11

import com.example.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

data class Details(val name: String, val followers: Int)
data class Tweet(val text: String)

private fun getFollowersNumber(): Int =
    throw Error("Service exception")

private suspend fun getUserName(): String {
    delay(500)
    return "marcinmoskala"
}

private suspend fun getTweets(): List<Tweet> {
    return listOf(Tweet("Hello, world"))
}

private suspend fun CoroutineScope.getUserDetails(): Details {
    val userName = async { getUserName() }
    val followersNumber = async { getFollowersNumber() }
    return Details(userName.await(), followersNumber.await())
}

fun main() = runBlocking {
    val details = try {
        getUserDetails()
    } catch (e: Error) {
        null
    }
    val tweets = async { getTweets() }
    log("User: $details")
    log("Tweets: ${tweets.await()}")
}