package ru.bogdanmsg.bmback.util

import ru.bogdanmsg.bmback.dto.Avatar
import ru.bogdanmsg.bmback.dto.User
import ru.bogdanmsg.bmback.entity.AvatarEntity
import ru.bogdanmsg.bmback.entity.UserEntity

fun UserEntity.toUser() = User(
    id = id.toString(),
    nickname = nickname,
    fio = fio,
    tag = tag,
    email = email,
    lastEntry = lastEntry,
    avatars = avatars.map { it.toAvatar() }
)

fun AvatarEntity.toAvatar() = Avatar(
    path = path,
    uploadedAt = uploadedAt
)
