package ru.bogdanmsg.bmback.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "auth_codes")
data class AuthCodeEntity(
    @ManyToOne(targetEntity = UserEntity::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    val userEntity: UserEntity? = null,

    @Column(name = "passcode", nullable = false)
    val passcode: String = "",

    @Column(name = "expire_date", nullable = false)
    val expireDate: Long = 0L,
) : BaseEntity()
