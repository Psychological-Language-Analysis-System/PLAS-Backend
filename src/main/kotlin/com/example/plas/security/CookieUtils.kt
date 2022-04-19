package com.example.plas.security

import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Component

@Component
class CookieUtils {

    companion object {
        private const val COOKIE_LIFE_TIME: Long = 864000
    }

    fun buildCookieIncludeToken(token: String): ResponseCookie {
        return ResponseCookie.from("accessToken", token)
            .httpOnly(true)
            .sameSite("lax")
            .maxAge(COOKIE_LIFE_TIME)
            .secure(true)
            .build()
    }
}