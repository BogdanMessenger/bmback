package ru.bogdanmsg.bmback.service

interface EmailSender {
    fun sendPassCodeMessage(to: String, passCode: String)
}
