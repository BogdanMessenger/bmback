package ru.bogdanmsg.bmback.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import ru.bogdanmsg.bmback.enum.AttachmentType

@Entity
class Attachment(
    @Column(nullable = false)
    var path: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var type: AttachmentType,
) : BaseEntity()
