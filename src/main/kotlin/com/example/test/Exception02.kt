package com.example.test

import kotlinx.coroutines.*

fun main() = runBlocking {
    val parentJob = currentCoroutineContext()[Job]!!
    println("runBlocking의 Job: $parentJob")

    supervisorScope {
        val childJob1 = launch {
            println("childJob1 시작")
            delay(200)
            println("childJob1 완료")
        }

        val childJob2 = launch {
            println("childJob2 시작")
            delay(100)
            throw RuntimeException("childJob2에서 예외 발생")
            println("childJob2 완료 (이 부분은 실행되지 않음)")
        }

        println("supervisorScope 내부 진행 중...")
        delay(300)
        println("supervisorScope 완료")
    }

    println("runBlocking 완료 (supervisorScope 내부 예외에 영향받지 않음)")
}