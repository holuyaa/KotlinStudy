package com.example.book.deepdive.chapter19

import com.example.util.log

private fun getSequence(): Sequence<String> = sequence {
    repeat(3) {
        Thread.sleep(1000)
        yield(("User$it"))
    }
}

fun main() {
    log("Started")
    val list = getSequence()
    log("Function started")
    list.forEach { log(it) }
}