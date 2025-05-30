package com.example.book.deepdive.chapter27

import com.example.util.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

private suspend fun makeConnection(): String {
    log("Creating connection")
    delay(1000)
    return "Connection"
}

// private val connection by lazy { makeConnection() } // compiler error

/*
private fun <T> suspendLazy(
    initializer: suspend () -> T
): suspend () -> T {
    TODO()
}
 */
/*
private val NOT_SET = Any()
private fun <T> suspendLazy(
    initializer: suspend () -> T
): suspend () -> T {
    val mutex = Mutex()
    var holder: Any? = NOT_SET

    return {
        @Suppress("UNCHECKED_CAST")
        if (holder !== NOT_SET) holder as T
        else mutex.withLock {
            if (holder == NOT_SET) holder = initializer()
            holder as T
        }
    }
}
 */

private fun <T> suspendLazy(
    initializer: suspend () -> T
): suspend () -> T {
    var initializer: (suspend () -> T)? = initializer
    val mutex = Mutex()
    var holder: Any? = Any()

    return {
        @Suppress("UNCHECKED_CAST")
        if (initializer == null) holder as T
        else mutex.withLock {
            initializer?.let {
                holder = it()
                initializer = null
            }
            holder as T
        }
    }
}

private val getConnection = suspendLazy { makeConnection() }

suspend fun main(): Unit = coroutineScope {
    log(getConnection())
    log(getConnection())
    log(getConnection())
}