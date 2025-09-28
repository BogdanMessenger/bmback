package ru.bogdanmsg.bmback.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.ManyToOne
import ru.bogdanmsg.bmback.enum.AttachmentType

@Entity
class AttachmentEntity(
    @Column(nullable = false)
    var path: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var type: AttachmentType,

    @ManyToOne()
    var messageEntity: MessageEntity
) : BaseEntity()