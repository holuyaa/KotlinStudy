package com.example.book.deepdive.chapter18

private fun m(i: Int): Int {
    print("m$i ")
    return i * i
}

fun main() {
//    listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val l = (1..10).toList()
        .map { m(it) }

    println(l)
    println(l.find { it > 10 })
    println(l.find { it > 10 })
    println(l.find { it > 10 })

//    sequenceOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val s = (1..10).asSequence()
        .map { m(it) }

    println(s)
    println(s.find { it > 10 })
    println(s.find { it > 10 })
    println(s.find { it > 10 })
}
