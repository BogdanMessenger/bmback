package ru.bogdanmsg.bmback

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.bogdanmsg.bmback.properties.AuthenticationProperties
import ru.bogdanmsg.bmback.properties.MinioProperties

@SpringBootApplication
// @EnableScheduling - чтобы не работала джоба на очистку аватарок
@EnableConfigurationProperties(
    AuthenticationProperties::class,
    MinioProperties::class
)
class BmBackApplication

fun main(args: Array<String>) {
    runApplication<BmBackApplication>(*args)
}
