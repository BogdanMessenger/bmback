package ru.bogdanmsg.bmback.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class UserEntity(
    @Column(unique = true, nullable = false)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var nickname: String,

    @Column(unique = true, nullable = false)
    var tag: String,

    @Column(nullable = false)
    var lastEntry: LocalDateTime,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val avatars: MutableList<AvatarEntity> = mutableListOf(),

    @OneToMany(mappedBy = "author")
    val messages: MutableList<MessageEntity> = mutableListOf(),

    @OneToMany(mappedBy = "user")
    val reactions: MutableList<ReactionEntity> = mutableListOf(),

    @OneToMany(mappedBy = "createdBy")
    var createdChats: MutableList<ChatEntity> = mutableListOf(),

    @ManyToMany(mappedBy = "users")
    val chats: MutableList<ChatEntity> = mutableListOf()
) : BaseEntity()
