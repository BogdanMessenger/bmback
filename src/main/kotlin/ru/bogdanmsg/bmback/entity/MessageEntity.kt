package ru.bogdanmsg.bmback.entity

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "message")
class MessageEntity(
    @Column(nullable = false, updatable = false)
    val sendAt: LocalDateTime,

    @Column(name = "payload", nullable = false)
    var text: String,

    @Column(nullable = false)
    var pinned: Boolean,

    @OneToOne()
    var forward: MessageEntity?,

    @ManyToOne()
    @JoinColumn(name = "chat_id")
    var chatEntity: ChatEntity,

    @ManyToOne()
    @JoinColumn(name = "author_id")
    val author: UserEntity,

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "uuid[]")
    val attachments: Array<UUID> = emptyArray(),

    @OneToMany(mappedBy = "messageEntity", cascade = [CascadeType.ALL])
    val reactionEntities: MutableList<ReactionEntity> = mutableListOf()
) : BaseEntity()
