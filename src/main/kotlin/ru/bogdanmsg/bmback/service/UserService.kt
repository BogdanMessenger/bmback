package ru.bogdanmsg.bmback.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import ru.bogdanmsg.bmback.entity.UserEntity

@Service
class UserService(
    private val minioService: MinioService
) {

    val log = LoggerFactory.getLogger(UserService::class.java)

    @Transactional
    fun uploadAvatar(user: UserEntity, file: MultipartFile): String {
        log.info("Uploading avatar start")

        val avatar = minioService.uploadFile(user, file)

        log.info("Avatar uploaded successfully for user")
        return avatar.path
    }
}
