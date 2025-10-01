package ru.bogdanmsg.bmback.entity

import jakarta.persistence.*

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

    @OneToMany(mappedBy = "userEntity", cascade = [CascadeType.ALL])
    val avatarEntity: MutableList<AvatarEntity> = mutableListOf(),

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    val messageEntity: MutableList<MessageEntity> = mutableListOf(),

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    val reactionEntity: MutableList<ReactionEntity> = mutableListOf(),

    @OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY)
    var createdChats: MutableList<ChatEntity> = mutableListOf(),

    @ManyToMany(mappedBy = "userEntities", fetch = FetchType.LAZY)
    val chatEntity: MutableList<ChatEntity> = mutableListOf()
) : BaseEntity()
