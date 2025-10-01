package ru.bogdanmsg.bmback.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "chat")
class ChatEntity(
    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    val createdAt: LocalDateTime,

    @ManyToOne()
    @JoinColumn(name = "user_id")
    val createdBy: UserEntity,

    @OneToMany(mappedBy = "chatEntity", cascade = [CascadeType.ALL])
    val messageEntities: MutableList<MessageEntity> = mutableListOf(),

    @ManyToMany()
    @JoinTable(
        name = "chats_users",
        joinColumns = [JoinColumn(name = "chat_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    val userEntities: MutableSet<UserEntity> = mutableSetOf(),
) : BaseEntity()
