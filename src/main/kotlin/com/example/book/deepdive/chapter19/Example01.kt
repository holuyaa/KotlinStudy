package com.example.book.deepdive.chapter19

import com.example.util.log

fun getList(): List<String> = List(3) {
    Thread.sleep(1000)
    "User$it"
}

fun main() {
    log("Started")
    val list = getList()
    log("Function started")
    list.forEach { log(it) }
}
