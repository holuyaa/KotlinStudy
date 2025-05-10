package com.example.test

import kotlinx.coroutines.*

fun main() = runBlocking {
    supervisorScope {
        val deferred = async {
            println("async 코루틴 시작")
            delay(100)
//            throw IllegalStateException("async 코루틴에서 예외 발생")
            "결과"
        }

        println("supervisorScope 내부")
        delay(200)

        deferred.await() // try-catch로 감싸지 않음
        println("await 이후 (이 부분은 실행되지 않음)")
    }

    println("supervisorScope 완료 (예외 발생에도 불구하고 실행됨)")
}