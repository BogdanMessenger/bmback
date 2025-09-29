package ru.bogdanmsg.bmback.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
class Reaction(
    @Column(nullable = false)
    var symbol: String,

    @ManyToOne()
    val message: Message,

    @ManyToOne()
    val user: User
) : BaseEntity()
