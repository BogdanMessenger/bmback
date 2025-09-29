package ru.bogdanmsg.bmback.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
class Avatar(
    @Column(nullable = false)
    var path: String,

    @Column(nullable = false)
    val uploadedAt: LocalDateTime,

    @ManyToOne()
    val user: User,
) : Base()
