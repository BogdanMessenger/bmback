package ru.bogdanmsg.bmback.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "avatars")
class AvatarEntity : BaseEntity() {
    @Column(nullable = false)
    var path: String = ""

    @Column(nullable = false)
    val uploadedAt: LocalDateTime = LocalDateTime.now()

    @ManyToOne()
    @JoinColumn(name = "user_id")
    lateinit var user: UserEntity

    @ManyToOne()
    @JoinColumn(name = "chat_id")
    lateinit var chat: ChatEntity
}
