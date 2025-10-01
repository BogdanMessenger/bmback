package ru.bogdanmsg.bmback.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "reaction")
class ReactionEntity(
    @Column(nullable = false)
    var symbol: String,

    @ManyToOne()
    @JoinColumn(name = "message_id")
    val messageEntity: MessageEntity,

    @ManyToOne()
    @JoinTable(name = "user_id")
    val userEntity: UserEntity
) : BaseEntity()
