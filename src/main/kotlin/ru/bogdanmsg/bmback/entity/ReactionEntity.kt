package ru.bogdanmsg.bmback.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
class ReactionEntity(
    @Column(nullable = false)
    var symbol: String,

    @ManyToOne()
    val messageEntity: MessageEntity,

    @ManyToOne()
    val userEntity: UserEntity
) : BaseEntity()