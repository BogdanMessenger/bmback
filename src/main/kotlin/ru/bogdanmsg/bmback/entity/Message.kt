package ru.bogdanmsg.bmback.entity

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.LocalDateTime
import java.util.*

@Entity
class Message(
    @Column(nullable = false, updatable = false)
    val sendAt: LocalDateTime,

    @Column(nullable = false)
    var text: String,

    @Column(nullable = false)
    var pinned: Boolean,

    @OneToOne()
    var forward: Message?,

    @ManyToOne()
    var chat: Chat,

    @ManyToOne()
    val author: User,

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "uuid[]")
    val attachments: Array<UUID> = emptyArray(),

    @OneToMany(mappedBy = "message", cascade = [CascadeType.ALL])
    @JoinColumn(name = "message_id")
    val reations: MutableList<Reaction> = mutableListOf()
) : BaseEntity()
