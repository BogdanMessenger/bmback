package ru.bogdanmsg.bmback.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.bogdanmsg.bmback.entity.ReactionEntity
import java.util.*

interface ReactionEntityRepository : JpaRepository<ReactionEntity, UUID>
