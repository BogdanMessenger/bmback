package ru.bogdanmsg.bmback.entity

import jakarta.persistence.*

@Entity
@Table(name = "reactions")
class ReactionEntity(
    @Column(nullable = false)
    var payload: String,

    @ManyToOne()
    @JoinColumn(name = "message_id")
    val messageEntity: MessageEntity,

    @ManyToOne()
    @JoinColumn(name = "user_id")
    val userEntity: UserEntity
) : BaseEntity()
