package ru.bogdanmsg.bmback.entity

import jakarta.persistence.*

@Entity
@Table(name = "reactions")
class ReactionEntity(
    @Column(nullable = false)
    var position: Short,

    @ManyToOne()
    @JoinColumn(name = "reactions_handbook_id")
    var reactionHandbook: ReactionHandbookEntity,

    @ManyToOne()
    @JoinColumn(name = "message_id")
    val message: MessageEntity,

    @ManyToOne()
    @JoinColumn(name = "user_id")
    val user: UserEntity
) : BaseEntity()
