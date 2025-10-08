package ru.bogdanmsg.bmback.dto.response

import ru.bogdanmsg.bmback.dto.User

data class AuthenticationResponse(
    val message: String? = null,
    val token: String? = null,
    val user: User,
)
