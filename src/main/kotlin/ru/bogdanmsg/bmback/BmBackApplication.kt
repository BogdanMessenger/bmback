package ru.bogdanmsg.bmback

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BmBackApplication

fun main(args: Array<String>) {
    runApplication<BmBackApplication>(*args)
}
