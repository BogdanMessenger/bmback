package ru.bogdanmsg.bmback.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "avatars")
class AvatarEntity(
    @Column(nullable = false)
    var path: String,

    @Column(nullable = false)
    val uploadedAt: LocalDateTime,

    @ManyToOne()
    @JoinColumn(name = "user_id")
    val userEntity: UserEntity,

    @ManyToOne()
    @JoinColumn(name = "chat_id")
    val chatEntity: ChatEntity
) : BaseEntity()
