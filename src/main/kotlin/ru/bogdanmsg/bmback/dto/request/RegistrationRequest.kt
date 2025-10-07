package ru.bogdanmsg.bmback.dto.request

data class RegistrationRequest(
    val nickname: String,
    val email: String,
    val password: String,
    val tag: String?,
    val fio: String,
)
