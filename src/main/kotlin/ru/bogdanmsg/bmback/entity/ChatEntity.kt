package ru.bogdanmsg.bmback.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany

// Ограничивать строго для двух пользователей или сразу сделать ManyToMany и валидировать количество участников в другом месте?

@Entity
class ChatEntity(

    @OneToMany(mappedBy = "chatEntity", cascade = [CascadeType.ALL])
    var messageEntities: MutableList<MessageEntity> = mutableListOf(),

    @ManyToMany()
    @JoinTable(
        name = "chats_users",
        joinColumns = [JoinColumn(name = "chat_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    var userEntities: MutableSet<UserEntity> = mutableSetOf(),

    ) : BaseEntity()