package ru.bogdanmsg.bmback.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.bogdanmsg.bmback.entity.Message
import java.util.*

interface MessageEntityRepository : JpaRepository<Message, UUID>
