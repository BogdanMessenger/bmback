package ru.bogdanmsg.bmback.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "messages")
class MessageEntity(
    @Column(nullable = false, updatable = false)
    val sentAt: LocalDateTime,

    @Column(name = "payload", nullable = false)
    var text: String,

    @Column(nullable = false)
    var pinned: Boolean,

    @Column(nullable = false)
    var hasAttachments: Boolean,

    @Column(nullable = false)
    var hasReactions: Boolean,

    @OneToOne()
    var forward: MessageEntity?,

    @ManyToOne()
    @JoinColumn(name = "chat_id")
    var chatEntity: ChatEntity,

    @ManyToOne()
    @JoinColumn(name = "author_id")
    val author: UserEntity,

    @OneToMany(mappedBy = "messageEntity", fetch = FetchType.LAZY)
    val attachmentEntities: MutableList<AttachmentEntity> = mutableListOf(),

    @OneToMany(mappedBy = "messageEntity", cascade = [CascadeType.ALL])
    val reactionEntities: MutableList<ReactionEntity> = mutableListOf()
) : BaseEntity()
