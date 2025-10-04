package ru.bogdanmsg.bmback.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "messages")
class MessageEntity : BaseEntity() {
    @Column(nullable = false, updatable = false)
    val sentAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "payload", nullable = false)
    lateinit var text: String

    @Column(nullable = false)
    var pinned: Boolean = false

    @Column(nullable = false)
    var hasAttachments: Boolean = false

    @Column(nullable = false)
    var hasReactions: Boolean = false

    @OneToOne(mappedBy = "threadRoot")
    var chatWhereRoot: ChatEntity? = null

    @OneToOne
    @JoinColumn(name = "forward_id")
    var forward: MessageEntity? = null

    @ManyToOne
    @JoinColumn(name = "chat_id")
    lateinit var chat: ChatEntity

    @ManyToOne
    @JoinColumn(name = "author_id")
    lateinit var author: UserEntity

    @OneToMany(mappedBy = "message")
    val attachments: MutableList<AttachmentEntity> = mutableListOf()

    @OneToMany(mappedBy = "message", cascade = [CascadeType.ALL])
    val reactions: MutableList<ReactionEntity> = mutableListOf()
}
