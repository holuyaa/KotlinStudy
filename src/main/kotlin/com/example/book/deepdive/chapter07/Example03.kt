package com.example.book.deepdive.chapter07

import com.example.util.log
import kotlinx.coroutines.CoroutineName
import kotlin.coroutines.CoroutineContext

fun main() {
    val ctx1: CoroutineContext = CoroutineName("Name1")
    log(ctx1[CoroutineName]?.name ?: "null")

    val ctx2: CoroutineContext = CoroutineName("Name2")
    log(ctx2[CoroutineName]?.name ?: "null")

    val ctx3 = ctx1 + ctx2
    log(ctx3[CoroutineName]?.name ?: "null")
}