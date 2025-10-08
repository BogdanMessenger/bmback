package ru.bogdanmsg.bmback.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import ru.bogdanmsg.bmback.entity.AvatarEntity
import ru.bogdanmsg.bmback.entity.UserEntity
import ru.bogdanmsg.bmback.properties.MinioProperties
import ru.bogdanmsg.bmback.repository.AvatarRepository

@Service
class UserService(
    private val minioService: MinioService,
    private val avatarRepository: AvatarRepository,
    private val minioProperties: MinioProperties
) {
    val log = LoggerFactory.getLogger(UserService::class.java)

    @Transactional
    fun uploadAvatar(user: UserEntity, file: MultipartFile): String {
        log.info("Uploading avatar start")

        val avatar = AvatarEntity().apply { this.user = user }

        avatar.path = minioService.uploadFile(avatar.id, file)

        avatarRepository.save(avatar)

        log.info("Avatar uploaded successfully for user ${user.id}")
        return "${minioProperties.endpoint}/${avatar.path}"
    }
}
