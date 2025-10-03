package ru.bogdanmsg.bmback.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "chats")
class ChatEntity(
    var name: String?,

    @Column(nullable = false)
    var isGroup: Boolean,

    @Column(nullable = false)
    val createdAt: LocalDateTime,

    @OneToMany(mappedBy = "chatEntity", cascade = [CascadeType.ALL])
    val avatarEntities: MutableList<AvatarEntity> = mutableListOf(),

    @OneToMany(mappedBy = "chatEntity", cascade = [CascadeType.ALL])
    val messageEntities: MutableList<MessageEntity> = mutableListOf(),

    @ManyToOne()
    @JoinColumn(name = "created_by")
    val createdBy: UserEntity,

    @ManyToMany()
    @JoinTable(
        name = "chats_users",
        joinColumns = [JoinColumn(name = "chat_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    val userEntities: MutableSet<UserEntity> = mutableSetOf(),
) : BaseEntity()
