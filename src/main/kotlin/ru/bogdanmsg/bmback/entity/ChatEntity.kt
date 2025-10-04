package ru.bogdanmsg.bmback.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "chats")
class ChatEntity : BaseEntity() {
    var name: String? = null

    @Column(nullable = false)
    var isGroup: Boolean = false

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

    @OneToOne
    @JoinColumn(name = "thread_root_id")
    var threadRoot: MessageEntity? = null

    @OneToMany(mappedBy = "chat", cascade = [CascadeType.ALL])
    val avatars: MutableList<AvatarEntity> = mutableListOf()

    @OneToMany(mappedBy = "chat", cascade = [CascadeType.ALL])
    val messages: MutableList<MessageEntity> = mutableListOf()

    @ManyToOne()
    @JoinColumn(name = "created_by")
    lateinit var createdBy: UserEntity

    @ManyToMany()
    @JoinTable(
        name = "chats_users",
        joinColumns = [JoinColumn(name = "chat_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    val users: MutableSet<UserEntity> = mutableSetOf()
}
