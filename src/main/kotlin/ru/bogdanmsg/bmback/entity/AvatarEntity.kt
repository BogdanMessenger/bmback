package ru.bogdanmsg.bmback.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "avatar")
class AvatarEntity(
    @Column(nullable = false)
    var path: String,

    @Column(nullable = false)
    val uploadedAt: LocalDateTime,

    @ManyToOne()
    @JoinColumn(name = "user_id")
    val userEntity: UserEntity,
) : BaseEntity()
