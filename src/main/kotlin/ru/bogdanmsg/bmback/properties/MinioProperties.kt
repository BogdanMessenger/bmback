package ru.bogdanmsg.bmback.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("app.minio")
data class MinioProperties(
    val endpoint: String,
    val username: String,
    val password: String,
    val bucket: String,
)
