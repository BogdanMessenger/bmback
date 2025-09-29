package ru.bogdanmsg.bmback.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.bogdanmsg.bmback.entity.User
import java.util.*

interface UserEntityRepository : JpaRepository<User, UUID>
