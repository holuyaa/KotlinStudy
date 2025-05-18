package com.example.book.deepdive.chapter23

import com.example.util.log
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList

data class User(val id: Int, val name: String) {
    override fun toString(): String = "{$id] $name"
}

suspend fun main() {
    val users = flowOf(
        User(1, "Alex"),
        User(1, "Bob"),
        User(2, "Bob"),
        User(2, "Celine"),
    )

    log(users.distinctUntilChangedBy { it.id }.toList())
    log(users.distinctUntilChangedBy { it.name }.toList())
    log(users.distinctUntilChanged { prev, next ->
        prev.id == next.id || prev.name == next.name
    }.toList())
}