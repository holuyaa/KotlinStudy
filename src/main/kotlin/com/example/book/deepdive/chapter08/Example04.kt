package com.example.book.deepdive.chapter08

import com.example.util.log
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val name = CoroutineName("Some name")
    val job = Job()

    launch(name + job) {
        val childName = coroutineContext[CoroutineName]
        log(childName == name)
        val childJob = coroutineContext[Job]
        log(childJob == job)
        log(childJob == job.children.first())
    }
}