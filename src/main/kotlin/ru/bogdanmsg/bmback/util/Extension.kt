package ru.bogdanmsg.bmback.util

import ru.bogdanmsg.bmback.dto.User
import ru.bogdanmsg.bmback.entity.UserEntity

fun UserEntity.toUser() = User(
    id = id.toString(),
    nickname = nickname,
    tag = tag,
    email = email,
    lastEntry = lastEntry
)
