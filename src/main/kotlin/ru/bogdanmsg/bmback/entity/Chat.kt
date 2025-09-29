package ru.bogdanmsg.bmback.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany

@Entity
class Chat(
    @OneToMany(mappedBy = "chat", cascade = [CascadeType.ALL])
    @JoinColumn(name = "chat_id")
    val messages: MutableList<Message> = mutableListOf(),

    @ManyToMany()
    @JoinTable(
        name = "chats_users",
        joinColumns = [JoinColumn(name = "chat_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    val users: MutableSet<User> = mutableSetOf(),
) : Base()
