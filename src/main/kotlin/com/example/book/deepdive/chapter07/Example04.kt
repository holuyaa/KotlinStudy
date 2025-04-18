package com.example.book.deepdive.chapter07

import com.example.util.log
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun main() {
    val empty: CoroutineContext = EmptyCoroutineContext
    log(empty[CoroutineName] ?: "null")
    log(empty[Job] ?: "null")

    val ctxName = empty + CoroutineName("Name1") + empty
    log(ctxName[CoroutineName] ?: "null")
}