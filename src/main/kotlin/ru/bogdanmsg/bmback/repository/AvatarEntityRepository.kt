package ru.bogdanmsg.bmback.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.bogdanmsg.bmback.entity.Avatar
import java.util.*

interface AvatarEntityRepository : JpaRepository<Avatar, UUID>
