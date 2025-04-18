package com.example.book.deepdive.chapter07

import com.example.util.log
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job

fun main() {
    val ctx = CoroutineName("Name1") + Job()
    log(ctx[CoroutineName]?.name ?: "null")
    log(ctx[Job]?.isActive ?: "null")

    val ctx2 = ctx.minusKey(CoroutineName)
    log(ctx2[CoroutineName]?.name ?: "null")
    log(ctx2[Job]?.isActive ?: "null")

    val ctx3 = (ctx + CoroutineName("Name2"))
        .minusKey(CoroutineName)
    log(ctx3[CoroutineName]?.name ?: "null")
    log(ctx3[Job]?.isActive ?: "null")
}