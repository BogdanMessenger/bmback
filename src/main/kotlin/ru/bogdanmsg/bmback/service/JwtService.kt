package ru.bogdanmsg.bmback.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import ru.bogdanmsg.bmback.entity.UserEntity
import ru.bogdanmsg.bmback.properties.AuthenticationProperties
import java.util.*

@Service
class JwtService(
    private val authenticationProperties: AuthenticationProperties,
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)
    private val signKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(authenticationProperties.token.secret))

    // Генератор токенов
    fun generateTokens(userDetails: UserEntity): Pair<String, String> {
        log.info("Token generating is began")

        return generateAccessToken(userDetails) to generateRefreshToken(userDetails)
    }

    private fun generateAccessToken(user: UserEntity): String =
        Jwts.builder()
            .subject(user.username)
            .claim("id", user.id)
            .expiration(Date(System.currentTimeMillis() + authenticationProperties.token.accessLifeTime))
            .signWith(signKey)
            .compact()

    private fun generateRefreshToken(user: UserEntity): String =
        Jwts.builder()
            .subject(user.username)
            .claim("id", user.id)
            .expiration(Date(System.currentTimeMillis() + authenticationProperties.token.refreshLifeTime))
            .signWith(signKey)
            .compact()

    // Валидаторы
    fun isTokenExpired(token: String): Boolean = extractClaims(token).expiration.before(Date())

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractClaims(token).subject
        return username == userDetails.username && !isTokenExpired(token)
    }

    // Экстракторы
    fun getUsername(token: String): String = extractClaims(token).subject

    private fun extractClaims(token: String): Claims = Jwts
        .parser()
        .verifyWith(signKey)
        .build()
        .parseSignedClaims(token)
        .payload
}
