package com.example.book.deepdive.chapter10

import com.example.util.log
import kotlinx.coroutines.*

data class Notification(val msg: String)

// Don't do this
@OptIn(ExperimentalCoroutinesApi::class)
private suspend fun sendNotifications(
    notifications: List<Notification>
) = withContext(SupervisorJob().also {
    log("#0 parent: ${it.parent}, current: ${it.job}")
}) {
    log("#1 parent: ${coroutineContext.job.parent}, current: ${coroutineContext.job}")
    notifications.forEachIndexed { i, notification ->
        launch {
            log("#1$i parent: ${coroutineContext.job.parent}, current: ${coroutineContext.job}")
            send(notification)
        }
    }
}

private suspend fun send(notification: Notification) {
    delay(200)
    if (notification.msg == ", ") throw Error("Some error")
    log("@@@@ $notification")
}

fun main(): Unit = runBlocking {
    val notifications = listOf(
        Notification("Hello"),
        Notification(", "),
        Notification("World")
    )
    sendNotifications(notifications)
    log("Done")
}