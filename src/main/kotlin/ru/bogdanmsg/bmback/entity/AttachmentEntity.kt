package ru.bogdanmsg.bmback.entity

import jakarta.persistence.*
import ru.bogdanmsg.bmback.enum.AttachmentType

@Entity
@Table(name = "attachments")
class AttachmentEntity(
    @Column(nullable = false)
    var path: String,

    @ManyToOne()
    @JoinColumn(name = "message_id")
    var messageEntity: MessageEntity,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var type: AttachmentType,
) : BaseEntity()
