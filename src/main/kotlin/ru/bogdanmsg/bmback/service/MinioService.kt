package ru.bogdanmsg.bmback.service

import io.minio.BucketExistsArgs
import io.minio.ListObjectsArgs
import io.minio.MakeBucketArgs
import io.minio.MinioClient
import io.minio.PutObjectArgs
import io.minio.RemoveObjectArgs
import io.minio.SetBucketPolicyArgs
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import ru.bogdanmsg.bmback.entity.AvatarEntity
import ru.bogdanmsg.bmback.entity.UserEntity
import ru.bogdanmsg.bmback.properties.MinioProperties
import ru.bogdanmsg.bmback.repository.AvatarRepository
import java.text.Normalizer

@Service
class MinioService(
    private val minioClient: MinioClient,
    private val minioProperties: MinioProperties,
    private val avatarRepository: AvatarRepository
) {

    private val log = LoggerFactory.getLogger(MinioService::class.java)

    @PostConstruct
    fun init() {
        log.info("Initializing MinIO bucket settings...")
        ensureBucketExists()
        setPublicAccess()
    }

    private fun ensureBucketExists() {
        try {
            val bucketExists = minioClient
                .bucketExists(BucketExistsArgs.builder().bucket(minioProperties.bucket).build())

            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.bucket).build())
                log.info("MinIO Bucket '${minioProperties.bucket}' created")
            } else {
                log.info("MinIO Bucket '${minioProperties.bucket}' already exists")
            }
        } catch (e: Exception) {
            log.error("Error checking/creating MinIO bucket: '${minioProperties.bucket}'", e)
        }
    }

    private fun setPublicAccess() {
        val policyJson = """
        {
          "Version": "2012-10-17",
          "Statement": [
            {
              "Effect": "Allow",
              "Principal": "*",
              "Action": ["s3:GetObject"],
              "Resource": ["arn:aws:s3:::${minioProperties.bucket}/*"]
            }
          ]
        }
        """.trimIndent()

        minioClient.setBucketPolicy(
            SetBucketPolicyArgs.builder()
                .bucket(minioProperties.bucket)
                .config(policyJson)
                .build()
        )

        log.info("MinIO Bucket '${minioProperties.bucket}' is now public (Read-Only).")
    }

    @Transactional
    fun uploadFile(user: UserEntity, file: MultipartFile): AvatarEntity {
        println("ИМЯ ФАЙЛА ${file.originalFilename}")
        val sanitizeFile = if (file.originalFilename.isNullOrEmpty()) "file" else file.originalFilename!!
        val sanitizedFilename = sanitizeFilename(sanitizeFile)

        val avatar = AvatarEntity().apply { this.user = user }

        val fileName = "${avatar.id}-$sanitizedFilename"

        log.info("Uploading file '$fileName' to MinIO bucket '${minioProperties.bucket}'...")

        minioClient.putObject(
            PutObjectArgs.builder()
                .bucket(minioProperties.bucket)
                .`object`(fileName)
                .stream(file.inputStream, file.size, -1)
                .contentType(file.contentType)
                .build()
        )

        val path = "${minioProperties.endpoint}/${minioProperties.bucket}/$fileName"
        avatar.path = path

        avatarRepository.save(avatar)

        log.info("File uploaded successfully: $fileName")
        return avatar
    }

    private fun sanitizeFilename(filename: String, maxLen: Int = 200): String {
        var s = Normalizer.normalize(filename, Normalizer.Form.NFKC).trim()

        s = s.replace("\\s+".toRegex(), "_")
        s = s.replace("[^\\p{L}0-9._-]".toRegex(), "")
        s = s.replace("_+".toRegex(), "_")
        s = s.trim('.', '_')
        if (s.isEmpty()) s = "file"
        if (s.length > maxLen) s = s.take(maxLen)

        return s
    }

    /**
     * Задача будет выполняться каждый день в 03:00 ночи.
     */
    @Scheduled(cron = "\${app.scheduled.clean-unused-files}")
    fun cleanUnusedFiles() {
        log.info("Starting cleanup of unused files...")

        try {
            val objects = minioClient.listObjects(
                ListObjectsArgs.builder().bucket(minioProperties.bucket).build()
            )

            for (result in objects) {
                val file = result.get()
                val path = file.objectName()

                val isFileUsed = avatarRepository.existsByPath(path)
                if (!isFileUsed) {
                    deleteFile(path)
                }
            }
        } catch (e: Exception) {
            log.error("Error retrieving file list from MinIO", e)
        }

        log.info("Cleanup process completed!")
    }

    private fun deleteFile(path: String) {
        try {
            minioClient.removeObject(
                RemoveObjectArgs.builder()
                    .bucket(minioProperties.bucket)
                    .`object`(path)
                    .build()
            )
            log.info("Deleted unused file: $path")
        } catch (e: Exception) {
            log.error("Error deleting file: $path", e)
        }
    }
}
