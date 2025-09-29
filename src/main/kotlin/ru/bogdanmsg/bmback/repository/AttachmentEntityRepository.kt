package ru.bogdanmsg.bmback.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.bogdanmsg.bmback.entity.Attachment
import java.util.*

interface AttachmentEntityRepository : JpaRepository<Attachment, UUID>
