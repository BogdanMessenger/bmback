package ru.bogdanmsg.bmback.dto

import java.time.LocalDateTime

data class User(
    val id: String,
    val email: String,
    val nickname: String,
    val fio: String,
    val tag: String,
    val lastEntry: LocalDateTime,
    val avatars: List<Avatar>
)
