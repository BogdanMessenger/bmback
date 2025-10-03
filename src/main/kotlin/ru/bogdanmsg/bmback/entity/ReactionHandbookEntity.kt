package ru.bogdanmsg.bmback.entity

import jakarta.persistence.*

@Entity
@Table(name = "reactions_handbook")
class ReactionHandbookEntity(
    @OneToMany(mappedBy = "reactionHandbook")
    var reactions: MutableList<ReactionEntity> = mutableListOf(),

    @Column(nullable = false)
    val payload: String
) : BaseEntity()