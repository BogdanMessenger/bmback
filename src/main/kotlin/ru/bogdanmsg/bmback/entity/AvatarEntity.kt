package ru.bogdanmsg.bmback.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import java.util.Date

@Entity
class AvatarEntity(

    @Column(nullable = false)
    var path: String,

    @Column(nullable = false)
    val date: Date,

    @ManyToOne()
    val userEntity: UserEntity,

    ) : BaseEntity()