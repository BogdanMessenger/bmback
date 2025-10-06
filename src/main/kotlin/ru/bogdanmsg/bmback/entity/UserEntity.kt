package ru.bogdanmsg.bmback.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class UserEntity : BaseEntity(), UserDetails {
    @Column(unique = true, nullable = false)
    var email: String = ""

    @Column(name = "password", nullable = false)
    var passwordHash: String = ""

    @Column(nullable = false)
    var nickname: String = ""

    @Column(unique = true, nullable = false)
    var tag: String = id.toString()

    @Column(nullable = false)
    lateinit var lastEntry: LocalDateTime

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    val avatars: MutableList<AvatarEntity> = mutableListOf()

    @OneToMany(mappedBy = "author")
    val messages: MutableList<MessageEntity> = mutableListOf()

    @OneToMany(mappedBy = "user")
    val reactions: MutableList<ReactionEntity> = mutableListOf()

    @OneToMany(mappedBy = "createdBy")
    var createdChats: MutableList<ChatEntity> = mutableListOf()

    @ManyToMany(mappedBy = "users")
    val chats: MutableList<ChatEntity> = mutableListOf()

    override fun getUsername() = this.email
    override fun getPassword() = this.passwordHash
    override fun getAuthorities() = listOf(SimpleGrantedAuthority("USER"))
}
