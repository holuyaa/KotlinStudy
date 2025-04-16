package com.example.book.deepdive.chapter11

import com.example.util.log
import com.example.util.logJob
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
    coroutineContext.logJob("#1")
    val userName = async {
        coroutineContext.logJob("#11")
        getUserName()
    }
    val followersNumber = async {
        coroutineContext.logJob("#12")
        getFollowersNumber()
    }
    log("Will be printed")
    return Details(userName.await(), followersNumber.await())
}

fun main() = runBlocking {
    val details = try {
        coroutineContext.logJob("#1")
        getUserDetails()
    } catch (e: Error) {
        null
    }
    val tweets = async { getTweets() }
    log("User: $details")
    log("Tweets: ${tweets.await()}")
}