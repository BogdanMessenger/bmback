package ru.bogdanmsg.bmback.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.bogdanmsg.bmback.entity.ChatEntity
import java.util.*

interface ChatRepository : JpaRepository<ChatEntity, UUID>
