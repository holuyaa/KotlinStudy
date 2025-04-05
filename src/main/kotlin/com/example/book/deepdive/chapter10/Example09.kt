package com.example.book.deepdive.chapter10

import com.example.util.log
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.supervisorScope

class MyException : Throwable()

suspend fun main() = supervisorScope {
    val str1 = async<String> {
        delay(1000)
        throw MyException()
    }

    val str2 = async<String> {
        delay(2000)
        "Text2"
    }

    try {
        log(str1.await())
    } catch (e: MyException) {
        log(e)
    }

    log(str2.await())
}
