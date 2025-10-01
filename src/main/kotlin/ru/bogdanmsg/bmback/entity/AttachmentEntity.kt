package ru.bogdanmsg.bmback.entity

import jakarta.persistence.*
import ru.bogdanmsg.bmback.enum.AttachmentType

@Entity
@Table(name = "attachment")
class AttachmentEntity(
    @Column(nullable = false)
    var path: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var type: AttachmentType,
) : BaseEntity()
