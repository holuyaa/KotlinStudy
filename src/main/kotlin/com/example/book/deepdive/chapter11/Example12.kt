package com.example.book.deepdive.chapter11

import com.example.util.log
import kotlinx.coroutines.*

class Repository(private val id: Int) {
    suspend fun fetchArticles(): List<String> {
        delay(1000)
        if (id == 5) throw IllegalStateException("error $id")
        return listOf("[$id] article#1", "[$id] article#2")
    }
}

private val repositories: List<Repository> = (1..10).map {
    Repository(it)
}.toList()

fun main() = runBlocking {
//    val a = coroutineScope {
    val a = supervisorScope {
        repositories
            .map { async { it.fetchArticles() } }
            .mapNotNull {
                try {
                    it.await()
                } catch (e: CancellationException) {
                    log(e)
                    null
                } catch (e: IllegalStateException) {
                    log(e)
                    null
                }
            }.flatten()
    }
    log(a)
}