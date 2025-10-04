package ru.bogdanmsg.bmback.dto.request

data class AuthenticationRequest(
    val login: String,
    val password: String,
)
