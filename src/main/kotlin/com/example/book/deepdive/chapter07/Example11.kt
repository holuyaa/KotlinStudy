package com.example.book.deepdive.chapter07

import com.example.util.log
import kotlinx.coroutines.withContext
import java.util.UUID
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

data class User(val id: String, val name: String)

abstract class UuidProviderContext : CoroutineContext.Element {
    abstract fun nextUuid(): String

    override val key: CoroutineContext.Key<*> = Key

    companion object Key : CoroutineContext.Key<UuidProviderContext>
}

class RealUuidProviderCotext : UuidProviderContext() {
    override fun nextUuid(): String =
        UUID.randomUUID().toString()
}

class FakeUuidProviderContext(
    private val fakeUuid: String
) : UuidProviderContext() {
    override fun nextUuid(): String = fakeUuid
}

suspend fun nextUuid(): String =
    checkNotNull(coroutineContext[UuidProviderContext]) {
        "UUidProviderContext not present"
    }
        .nextUuid()

// A function to test
suspend fun makeUser(name: String) = User(
    id = nextUuid(),
    name = name
)

suspend fun main(): Unit {
    // production
    withContext(RealUuidProviderCotext()) {
        log(makeUser("Michal"))
    }

    withContext(FakeUuidProviderContext("FAKE_UUID")) {
        val user = makeUser("Michal")
        log(user)
        assert(User("FAKE_UUID", "Michal") == user)
    }
}