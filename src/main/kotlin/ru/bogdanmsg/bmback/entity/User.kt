package ru.bogdanmsg.bmback.entity

import jakarta.persistence.*

@Entity
class User(
    @Column(unique = true, nullable = false)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var nickname: String,

    @Column(unique = true, nullable = false)
    var tag: String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id")
    val avatar: MutableList<Avatar> = mutableListOf(),

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val message: MutableList<Message> = mutableListOf(),

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val reaction: MutableList<Reaction> = mutableListOf(),

    @ManyToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val chat: MutableList<Chat> = mutableListOf()
) : Base()
