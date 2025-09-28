package ru.bogdanmsg.bmback.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.bogdanmsg.bmback.entity.AvatarEntity
import java.util.*

interface AvatarEntityRepository : JpaRepository<AvatarEntity, UUID>