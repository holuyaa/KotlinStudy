package com.example.util

import java.time.LocalDateTime

fun log(msg: Any) = println("${LocalDateTime.now()} [${Thread.currentThread().name}] $msg")
