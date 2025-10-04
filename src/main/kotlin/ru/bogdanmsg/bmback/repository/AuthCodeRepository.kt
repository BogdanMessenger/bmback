package ru.bogdanmsg.bmback.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.bogdanmsg.bmback.entity.AuthCodeEntity
import ru.bogdanmsg.bmback.entity.UserEntity
import java.util.UUID

interface AuthCodeRepository : JpaRepository<AuthCodeEntity, UUID> {

    fun findAuthCodeEntitiesByUserEntity(userEntity: UserEntity): List<AuthCodeEntity>
    fun deleteAuthCodeEntitiesByUserEntity(userEntity: UserEntity)
}
