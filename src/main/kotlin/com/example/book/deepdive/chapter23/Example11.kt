package com.example.book.deepdive.chapter23

import com.example.util.log

fun main() {
    val list = listOf(1, 2, 3, 4)
    val res = list.fold(0) { acc, i -> acc + i }
    log(res)
    val res2 = list.fold(1) { acc, i -> acc * i }
    log(res2)
}