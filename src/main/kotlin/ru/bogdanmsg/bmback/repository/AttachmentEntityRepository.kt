package ru.bogdanmsg.bmback.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.bogdanmsg.bmback.entity.AttachmentEntity
import java.util.*

interface AttachmentEntityRepository : JpaRepository<AttachmentEntity, UUID>