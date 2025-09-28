package ru.bogdanmsg.bmback.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import java.util.*

@Entity
class MessageEntity(
    @Column(nullable = false, updatable = false)
    val date: Date,

    @Column(nullable = false)
    var text: String,

    @Column(nullable = false)
    var pinned: Boolean,

    @OneToOne()
    var forward: MessageEntity?,

    @ManyToOne()
    var chatEntity: ChatEntity,

    @ManyToOne()
    val author: UserEntity,

    @OneToMany(mappedBy = "messageEntity", cascade = [CascadeType.ALL])
    var attachmentEntities: MutableList<AttachmentEntity> = mutableListOf(),

    @OneToMany(mappedBy = "messageEntity", cascade = [CascadeType.ALL])
    var reations: MutableList<ReactionEntity> = mutableListOf()

) : BaseEntity()