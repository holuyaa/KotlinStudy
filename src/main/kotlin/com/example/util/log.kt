package com.example.util

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.job
import java.time.LocalDateTime
import kotlin.coroutines.CoroutineContext

fun log(msg: Any) = println("${LocalDateTime.now()} [${Thread.currentThread().name}] $msg")

@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineContext.logJob(msg: Any = "") {
    log("$msg parent: ${job.parent}, current: $job")
}