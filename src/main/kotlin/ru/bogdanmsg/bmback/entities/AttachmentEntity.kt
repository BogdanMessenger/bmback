package ru.bogdanmsg.bmback.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import ru.bogdanmsg.bmback.enums.AttachmentType

@Entity
class AttachmentEntity(
    @Column(nullable = false)
    var path: String,

    @Column(nullable = false)
    var type: AttachmentType,

    @ManyToOne()
    var messageEntity: MessageEntity
) : BaseEntity()