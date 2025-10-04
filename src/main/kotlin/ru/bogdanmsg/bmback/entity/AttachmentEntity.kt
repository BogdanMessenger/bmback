package ru.bogdanmsg.bmback.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import ru.bogdanmsg.bmback.enum.AttachmentType

@Entity
@Table(name = "attachments")
class AttachmentEntity : BaseEntity() {
    @Column(nullable = false)
    var path: String = ""

    @ManyToOne()
    @JoinColumn(name = "message_id")
    lateinit var message: MessageEntity

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    lateinit var type: AttachmentType
}
