package ru.bogdanmsg.bmback.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.bogdanmsg.bmback.entity.MessageEntity
import java.util.*

interface MessageEntityRepository : JpaRepository<MessageEntity, UUID>
