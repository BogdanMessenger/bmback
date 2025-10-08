package ru.bogdanmsg.bmback.dto

import java.time.LocalDateTime

data class Avatar(
    val path: String,
    val uploadedAt: LocalDateTime
)
