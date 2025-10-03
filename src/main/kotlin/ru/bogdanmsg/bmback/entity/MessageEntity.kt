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
    val chatWhereRoot: ChatEntity,

    @OneToOne()
    var forward: MessageEntity?,

    @ManyToOne()
    @JoinColumn(name = "chat_id")
    var chat: ChatEntity,

    @ManyToOne()
    @JoinColumn(name = "author_id")
    val author: UserEntity,

    @OneToMany(mappedBy = "message")
    val attachments: MutableList<AttachmentEntity> = mutableListOf(),

    @OneToMany(mappedBy = "message", cascade = [CascadeType.ALL])
    val reactions: MutableList<ReactionEntity> = mutableListOf()
) : BaseEntity()
