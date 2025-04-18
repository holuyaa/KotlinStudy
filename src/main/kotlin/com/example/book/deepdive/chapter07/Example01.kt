package com.example.book.deepdive.chapter07

import com.example.util.log
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

fun main() {
    val ctx: CoroutineContext = CoroutineName("A name")

    val coroutineName: CoroutineName? = ctx[CoroutineName]
    log(coroutineName?.name ?: "null")
    val job: Job? = ctx[Job]
    log(job ?: "null")
}