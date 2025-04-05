package com.example.book.deepdive.chapter11

import com.example.util.log
import kotlinx.coroutines.*

class Profile
class UserDataRepository {
    suspend fun getName(): String {
        delay(500)
        return "Mike"
    }

    suspend fun getFriends(): List<String> {
        delay(500)
        return listOf("Jane")
    }

    suspend fun getProfile(): Profile {
        delay(500)
        return Profile()
    }

    suspend fun notifyProfileShown() {
        delay(250)
        throw Error()
    }
}
data class User2(
    private val name: String,
    private val friends: List<String>,
    private val profile: Profile
)
class UserDataView {
    suspend fun show(user: User2) {
        delay(300)
        log(user.toString())
    }
}

class ShowUserDataUseCase(
    private val repo: UserDataRepository,
    private val view: UserDataView,
    private val analyticsScope: CoroutineScope
) {
    suspend fun showUserData() = coroutineScope {
        val name = async { repo.getName() }
        val friends = async { repo.getFriends() }
        val profile = async { repo.getProfile() }
        val user = User2(
            name = name.await(),
            friends = friends.await(),
            profile = profile.await()
        )
        view.show(user)
        analyticsScope.launch { repo.notifyProfileShown() }
    }
}

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, e ->
        log(e)
    }
    val useCase = ShowUserDataUseCase(
        repo = UserDataRepository(),
        view = UserDataView(),
        analyticsScope = CoroutineScope(CoroutineName("AnalyticsScope") + handler)
    )
    log("Before")
    useCase.showUserData()
    log("Done")
    delay(2000)
}