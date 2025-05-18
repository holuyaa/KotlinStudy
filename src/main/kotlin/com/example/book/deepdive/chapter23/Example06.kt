package com.example.book.deepdive.chapter23

import com.example.util.log
import kotlinx.coroutines.flow.flow

suspend fun main() {
    flow map@ {
        flow flowOf@{
            for (element in arrayOf('a', 'b'))       {
                this@flowOf.emit(element)
            }
        }.collect { value ->
            this@map.emit(value.uppercase())
        }
    }.collect { log(it) }
}