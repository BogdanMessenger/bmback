package ru.bogdanmsg.bmback.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.bogdanmsg.bmback.entity.AvatarEntity
import java.util.*

interface AvatarRepository : JpaRepository<AvatarEntity, UUID> {
    fun existsByPath(path: String): Boolean
    fun findByPath(path: String): AvatarEntity?
}
