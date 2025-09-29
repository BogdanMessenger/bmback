package ru.bogdanmsg.bmback.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.bogdanmsg.bmback.entity.Chat
import java.util.*

interface ChatEntityRepository : JpaRepository<Chat, UUID>
