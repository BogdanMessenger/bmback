package ru.bogdanmsg.bmback.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "reactions_handbook")
class ReactionHandbookEntity : BaseEntity() {
    @OneToMany(mappedBy = "reactionHandbook")
    var reactions: MutableList<ReactionEntity> = mutableListOf()

    @Column(nullable = false)
    var payload: String = ""
}
