package com.example.book.deepdive.chapter18

import com.example.util.log

fun main() {
    val l = buildList {
        repeat(3) {
            add("User$it")
            log("L: Added User")
        }
    }

    val l2 = l.map {
        log("L: Processing")
        "Processed $it"
    }

    val s = sequence {
        repeat(3) {
            yield("User$it")
            log("S: Added User")
        }
    }

    val s2 = s.map {
        log("S: Processing")
        "Processed $it"
    }
}