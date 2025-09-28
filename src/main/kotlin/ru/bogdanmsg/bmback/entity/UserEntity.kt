package ru.bogdanmsg.bmback.entity

import jakarta.persistence.*

@Entity
class UserEntity(
    @Column(unique = true, nullable = false)
    var email: String,

    @Column(unique = true, nullable = false)
    var password: String,

    @Column(nullable = false)
    var nickname: String,

    @Column(unique = true, nullable = false)
    var tag: String,

    @OneToMany(mappedBy = "userEntity", cascade = [CascadeType.ALL])
    var avatarEntities: MutableList<AvatarEntity> = mutableListOf(),

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    var messageEntities: MutableList<MessageEntity> = mutableListOf(),

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    var reactionEntities: MutableList<ReactionEntity> = mutableListOf(),

    @ManyToMany(mappedBy = "userEntities", fetch = FetchType.LAZY)
    var chatEntities: MutableList<ChatEntity> = mutableListOf()

) : BaseEntity() {
    fun addAvatar(block: UserEntity.() -> AvatarEntity) {
        avatarEntities.add(block())
    }

    fun setAvatars(block: UserEntity.() -> MutableList<AvatarEntity>) {
        avatarEntities.clear()
        avatarEntities.addAll(block())
    }
}