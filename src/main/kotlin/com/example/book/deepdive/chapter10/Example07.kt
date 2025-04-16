package com.example.book.deepdive.chapter10

import com.example.util.log
import com.example.util.logJob
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

data class Notification(val msg: String)

// Don't do this
private suspend fun sendNotifications(
    notifications: List<Notification>
) = withContext(SupervisorJob().also {
    coroutineContext.logJob()
}) {
    coroutineContext.logJob("#1")
    notifications.forEachIndexed { i, notification ->
        launch {
            coroutineContext.logJob("#1$i")
            send(notification)
        }
    }
}

private suspend fun send(notification: Notification) {
    delay(200)
    if (notification.msg == ", ") throw Error("Some error")
    log("@@@@ $notification")
}

/**
 * coroutine#1(BlockingCoroutine)
 * SupervisorJobImpl -> coroutine#1(UnDispatchedCoroutine) -> coroutine#2
 *                                                         -> coroutine#3
 *                                                         -> coroutine#4
 */
fun main(): Unit = runBlocking {
    val notifications = listOf(
        Notification("Hello"),
        Notification(", "),
        Notification("World")
    )
    sendNotifications(notifications)
    log("Done")
}