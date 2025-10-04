package ru.bogdanmsg.bmback.util.exception

class UserNotFoundException(message: String = "User not found") : AuthenticationException(message)
