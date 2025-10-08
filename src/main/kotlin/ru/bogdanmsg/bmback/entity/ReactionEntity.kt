package ru.bogdanmsg.bmback.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "reactions")
class ReactionEntity : BaseEntity() {
    @Column(nullable = false)
    var position: Short = 0

    @ManyToOne()
    @JoinColumn(name = "reaction_handbook_id")
    lateinit var reactionHandbook: ReactionHandbookEntity

    @ManyToOne()
    @JoinColumn(name = "message_id")
    lateinit var message: MessageEntity

    @ManyToOne()
    @JoinColumn(name = "user_id")
    lateinit var user: UserEntity
}
