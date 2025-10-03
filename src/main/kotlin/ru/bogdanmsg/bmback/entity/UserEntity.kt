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

    @OneToMany(mappedBy = "userEntity", cascade = [CascadeType.ALL])
    val avatarEntities: MutableList<AvatarEntity> = mutableListOf(),

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    val messageEntities: MutableList<MessageEntity> = mutableListOf(),

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    val reactionEntities: MutableList<ReactionEntity> = mutableListOf(),

    @OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY)
    var createdChats: MutableList<ChatEntity> = mutableListOf(),

    @ManyToMany(mappedBy = "userEntities", fetch = FetchType.LAZY)
    val chatEntities: MutableList<ChatEntity> = mutableListOf()
) : BaseEntity()
